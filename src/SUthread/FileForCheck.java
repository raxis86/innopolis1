package SUthread;

import java.io.*;
import java.util.*;

/**
 * Created by raxis on 10.12.2016.
 * Класс для открытия и обработки тектового файла
 */
public class FileForCheck implements UReadable {
    private FileInputStream fileInputStream;
    private InputStreamReader fileReader;

    @Override
    public BufferedReader open(String resource) throws UnsupportedEncodingException, FileNotFoundException {
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
