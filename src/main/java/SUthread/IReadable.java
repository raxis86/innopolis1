package SUthread;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created by raxis on 10.12.2016.
 * Интерфейс объявляющий методы для открытия ресурса
 * и получения пути к нему
 */
public interface IReadable extends Closeable {
    /**
     * Объявляет метод для открытия ресурса
     * @return BufferedReader - буферизованное содержимое ресурса
     * @throws IOException
     */
    public BufferedReader open()throws IOException;

    /**
     * Объявляет метод для получения пути к ресурсу
     * @return String - путь к ресурсу
     */
    public String getResourcePath();
}
