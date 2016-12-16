package SUthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by raxis on 11.12.2016.
 * Класс для открытия url-ресурса
 */
public class UrlForCheck implements IReadable {
    @Override
    public BufferedReader open(String resource) throws IOException {
        URL url= new URL(resource);
        URLConnection urlConnection = url.openConnection();
        return new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }

    @Override
    public void close() throws IOException {

    }
}
