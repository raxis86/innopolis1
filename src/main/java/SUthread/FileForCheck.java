package SUthread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by raxis on 10.12.2016.
 * Класс для открытия файлового ресурса и
 * помещения его в BufferedReader
 */
public class FileForCheck implements IReadable {
    private static final Logger logger = LoggerFactory.getLogger(FileForCheck.class);

    private FileInputStream fileInputStream;
    private InputStreamReader fileReader;
    private BufferedReader bufferedReader;
    private String resource;

    /**Конструктор класса
     * @param resourcePath - путь к файлу
     */
    public FileForCheck(String resourcePath){
        logger.debug("FileForCheck({})",resourcePath);
        this.resource=resourcePath;
    }


    /**Метод для открытия файла и записи содержимого в BufferedReader
     * @return BufferedReader
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    @Override
    public BufferedReader open() throws UnsupportedEncodingException, FileNotFoundException {
        logger.debug("open() call");
        fileInputStream = new FileInputStream(resource);
        fileReader = new InputStreamReader(fileInputStream,"Cp1251");
        bufferedReader = new BufferedReader(fileReader);
        logger.debug("open(): return {}",bufferedReader);
        return bufferedReader;
    }

    /**Метод для возвращения пути файла, переданного в конструкторе
     * @return String - путь к файлу
     */
    @Override
    public String getResourcePath() {
        logger.debug("getResourcePath(): return {}",resource);
        return resource;
    }

    /**
     * Метод для закрытия всех открытых потоков
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        logger.debug("close() call");
        fileInputStream.close();
        fileReader.close();
        bufferedReader.close();
    }
}
