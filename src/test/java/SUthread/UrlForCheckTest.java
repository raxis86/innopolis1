package SUthread;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import sun.misc.IOUtils;
import sun.net.www.http.HttpClient;
import sun.nio.ch.IOUtil;
import sun.security.util.Resources;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by raxis on 16.12.2016.
 */
@Deprecated
public class UrlForCheckTest {
    private static Logger logger = Logger.getLogger(UrlForCheckTest.class);

    private UrlForCheck urlForCheck;
    private static File file;
    private static BufferedWriter bufferedWriter;
    private static final String testText = "Open url test success!";

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"FileForCheck\"");

    }

    @Before
    public void before() throws Exception {
        //this.urlForCheck = new UrlForCheck();
        file = folder.newFile("myfile.txt");
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bufferedWriter.write(testText);
        bufferedWriter.close();

    }

    @Test
    public void open() throws Exception {
        /*final URLConnection mockConnection = mock(URLConnection.class);
        given(mockConnection.getInputStream()).willReturn(
                new FileInputStream(file));

        final URLStreamHandler handler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(final URL arg0)
                    throws IOException {
                return mockConnection;
            }
        };

        final URL url = new URL("http", "foo.bar", 80, "", handler);*/

        //URLConnection urlConnection = url.openConnection();
        //System.out.println(new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine());

        System.out.println("");
        //URL testurl = new URL("https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html");
        //System.out.println(url.getHost());
        //System.out.println(urlForCheck.open("http://foo.bar"));
        //System.out.println(urlForCheck.open(urlConnectionMock.getURL()));
    }

    @Test
    public void close() throws Exception {

    }

}