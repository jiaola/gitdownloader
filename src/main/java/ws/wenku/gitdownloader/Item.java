package ws.wenku.gitdownloader;

import lombok.Data;

/**
 * @author Dazhi Jiao
 */
@Data
public class Item {
    private String name;
    private String path;
    private String sha;
    private long size;
    private String url;
    private String html_url;
    private String git_url;
    private String download_url;
    private String type;
}
