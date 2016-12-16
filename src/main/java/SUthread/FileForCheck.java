package SUthread;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by raxis on 10.12.2016.
 * Класс для открытия файлового ресурса
 */
public class FileForCheck implements IReadable {
    private static final Logger logger = Logger.getLogger(FileForCheck.class);
    private FileInputStream fileInputStream;
    private InputStreamReader fileReader;

    @Override
    public BufferedReader open(String resource) throws UnsupportedEncodingException, FileNotFoundException {
        logger.info(resource);
        fileInputStream = new FileInputStream(resource);
        fileReader = new InputStreamReader(fileInputStream,"Cp1251");
        return new BufferedReader(fileReader);
    }

    @Override
    public void close() throws IOException {
        fileInputStream.close();
        fileReader.close();
    }
}
