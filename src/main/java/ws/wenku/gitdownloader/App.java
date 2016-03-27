package ws.wenku.gitdownloader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public App() {
        super();
    }

    public boolean downloadDir(String url, File dir) {
        if (!dir.exists()) dir.mkdirs();
        List<Item> list = listUrl(url);
        final List<Boolean> result = new ArrayList<>();
        list.forEach(item -> {
            if ("dir".equals(item.getType())) { //dir
                File newDir = new File(dir, item.getName());
                result.add(downloadDir(item.getUrl(), newDir));
            } else { // file
                File file = FileUtils.getFile(dir, item.getName());
                result.add(downloadFile(item.getDownload_url(), file));
            }
        });
        return !result.contains(Boolean.FALSE);
    }

    public boolean downloadFile(String url, File file) {
        boolean success = true;
        try {
            FileUtils.copyURLToFile(new URL(url), file);
        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
            success = false;
        }
        return success;
    }

    public List<Item> listUrl(String url) {
        String json = getStringFromUrl(url);
        Type listType = new TypeToken<List<Item>>(){}.getType();
        List<Item> list = new Gson().fromJson(json, listType);
        return list;
    }

    public String getStringFromUrl(String url) {
        String out = null;
        try (Scanner scanner = new Scanner(new URL(url).openStream(), "UTF-8")) {
            out = scanner.useDelimiter("\\A").next();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return out;
    }


    public static void main( String[] args ) {
        if (args.length != 2) {
            System.err.println("Usage: java ws.wenku.gitdownloader.App <URL> <DIR>");
            System.exit(1);
        }

        App app = new App();
        File dir = new File(args[1]);
        if (!dir.exists()) dir.mkdirs();
        boolean result = app.downloadDir(args[0], dir);
        if (!result) {
            System.out.println("Completed but there were problems downloading the folder");
        }
    }
}
