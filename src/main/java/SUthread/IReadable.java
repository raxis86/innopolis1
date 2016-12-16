package SUthread;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raxis on 10.12.2016.
 * Интерфейс для открытия ресурсов на проверку
 * Содержит метод метод open
 */
public interface IReadable extends Closeable {
    public BufferedReader open(String resource)throws IOException;
}
