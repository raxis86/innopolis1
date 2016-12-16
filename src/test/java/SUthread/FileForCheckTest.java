package SUthread;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by raxis on 15.12.2016.
 * Модульный тест для класса FileForCheck
 */
public class FileForCheckTest {
    private static Logger logger = Logger.getLogger(FileForCheck.class);

    private FileForCheck fileForCheck;
    private static File file;
    private static BufferedWriter bufferedWriter;
    private static final String testText = "Ехали медведи на велосипеде";

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();


    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"FileForCheck\"");

    }

    @Before
    public void before() throws Exception {
        file = folder.newFile("myfile.txt");
        this.fileForCheck = new FileForCheck();
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Cp1251"));
        bufferedWriter.write(testText);
        bufferedWriter.close();
    }



    /**
     * Проверяем то, что метод открывает файл
     * @throws Exception
     */
    @Test
    public void open() throws Exception {
        assertEquals(testText,fileForCheck.open(file.getPath()).readLine());
    }

    /**
     * Проверка того, что буфер, созданный методом open закрывается
     * @throws Exception
     */
    @Test
    public void close() throws Exception {
        BufferedReader bufferedReader=fileForCheck.open(file.getPath());
        String s="";
        fileForCheck.close();
        try {
            bufferedReader.ready();
        }catch (Exception e){
            s=e.getMessage();
        }

        assertEquals(s,"Stream closed");

    }

}