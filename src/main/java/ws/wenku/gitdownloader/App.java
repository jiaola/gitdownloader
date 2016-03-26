package ws.wenku.gitdownloader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class App {
    public App() {
        super();
    }

    public boolean downloadDir(String url) {

        return false;
    }

    public boolean downloadFile(String url) {
        return false;
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
            ex.printStackTrace();
        }
        return out;
    }


    public static void main( String[] args ) {

    }
}
