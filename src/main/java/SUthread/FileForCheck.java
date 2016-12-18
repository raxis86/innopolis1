package SUthread;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by raxis on 10.12.2016.
 * Класс для открытия файлового ресурса и
 * помещения его в BufferedReader
 */
public class FileForCheck implements IReadable {
    private static final Logger logger = Logger.getLogger(FileForCheck.class);

    private FileInputStream fileInputStream;
    private InputStreamReader fileReader;
    private BufferedReader bufferedReader;
    private String resource;

    /**Конструктор класса
     * @param resourcePath - путь к файлу
     */
    public FileForCheck(String resourcePath){
        this.resource=resourcePath;
    }


    /**Метод для открытия файла и записи содержимого в BufferedReader
     * @return BufferedReader
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    @Override
    public BufferedReader open() throws UnsupportedEncodingException, FileNotFoundException {
        logger.debug("opeb():resouce:"+resource);
        fileInputStream = new FileInputStream(resource);
        fileReader = new InputStreamReader(fileInputStream,"Cp1251");
        bufferedReader = new BufferedReader(fileReader);
        return bufferedReader;
    }

    /**Метод для возвращения пути файла, переданного в конструкторе
     * @return String - путь к файлу
     */
    @Override
    public String getResourcePath() {
        return resource;
    }

    /**
     * Метод для закрытия всех открытых потоков
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        fileInputStream.close();
        fileReader.close();
        bufferedReader.close();
    }
}
