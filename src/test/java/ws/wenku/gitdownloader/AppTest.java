package ws.wenku.gitdownloader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        new App();
    }

    public void testGetStringFromUrl() {
        App app = new App();
        String out = app.getStringFromUrl("https://www.google.com");
        assertNotNull(out);
        assertNotSame("", out);
    }

    public void testListUrl() {
        App app = new App();
        List<Item> list = app.listUrl("https://api.github.com/repos/jiaola/gitdownloader/contents/src");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    public void testDownloadUrl() throws Exception {
        App app = new App();
        String url = "https://raw.githubusercontent.com/jiaola/gitdownloader/master/src/main/java/ws/wenku/gitdownloader/App.java";
        File tempFile =  File.createTempFile("App", "java");
        tempFile.deleteOnExit();
        boolean result = app.downloadFile(url, tempFile);
        assertTrue(tempFile.length() > 0);
        assertTrue(result);
    }

    public void testDownloadDir() throws Exception {
        App app = new App();
        String url = "https://api.github.com/repos/jiaola/gitdownloader/contents/src";
        File tempDir = Files.createTempDirectory("gitdownloader_").toFile();
        tempDir.deleteOnExit();
        boolean result = app.downloadDir(url, tempDir);
        assertTrue(result);
        File[] dirs = tempDir.listFiles(File::isDirectory);
        assertEquals(2, dirs.length);
    }
}
