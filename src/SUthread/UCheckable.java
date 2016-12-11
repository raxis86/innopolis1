package SUthread;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created by raxis on 11.12.2016.
 * Инткрфейс для чекера
 */
public interface UCheckable {
    public String checkOnUnq(BufferedReader reader) throws IOException;
}
