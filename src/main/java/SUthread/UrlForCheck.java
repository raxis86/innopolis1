package SUthread;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by raxis on 11.12.2016.
 * Класс для открытия url-ресурса
 */
@Deprecated
public class UrlForCheck implements IReadable {
    private static final Logger logger = Logger.getLogger(UrlForCheck.class);

    private String resourcePath;
    private URL url;
    private URLConnection urlConnection;
    private BufferedReader bufferedReader;

    public UrlForCheck(String resourcePath){
        this.resourcePath=resourcePath;
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    @Override
    public BufferedReader open() throws IOException {
        url= new URL(resourcePath);
        urlConnection = url.openConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        return bufferedReader;
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }
}
