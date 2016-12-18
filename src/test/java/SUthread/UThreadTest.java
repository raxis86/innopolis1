package SUthread;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raxis on 16.12.2016.
 * Модульный тест для класса UThread
 */
public class UThreadTest {
    private static Logger logger = Logger.getLogger(UThreadTest.class);

    //private List<Thread> threadList = new ArrayList<>();
    private UThread thread;
    private ThreadGroup threadGroup;
    private IReadable resForOpen;
    private File file, fileWithNoRus, fileWithRepWords;
    private BufferedWriter bufferedWriter;
    private final String testText = "Ехали медведи на велосипеде";
    private final String testTextRep = "Ехали медведи на велосипеде медведи";
    private final String testTextNoRus = "Ехали медведи на велосипеде. Stop.";

    /*private static boolean isAliveThreads(List<Thread> threads){
        for(Thread t:threads){
            if(t.isAlive())return true;
        }
        return false;
    }*/

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"UThreadTest\"");

    }

    @Before
    public void before() throws Exception {
        threadGroup = new ThreadGroup("Group");
        file = folder.newFile("resource.txt");
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Cp1251"));
        bufferedWriter.write(testText);
        bufferedWriter.close();
        fileWithNoRus = folder.newFile("resourceWithNoRus.txt");
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileWithNoRus),"Cp1251"));
        bufferedWriter.write(testTextNoRus);
        bufferedWriter.close();
        fileWithRepWords = folder.newFile("resourceWithRepWords.txt");
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileWithRepWords),"Cp1251"));
        bufferedWriter.write(testTextRep);
        bufferedWriter.close();

        Class c = Monitor.class;
        Field[] fields = c.getDeclaredFields();
        for(Field field:fields){
            if("alarm".equals(field.getName())){
                field.setAccessible(true);
                field.setBoolean(c,false);
            }
        }
    }

    /**
     * Тестирование метода run() c правильными ресурсами
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        resForOpen = new FileForCheck(file.getPath());
        thread = new UThread(threadGroup,resForOpen);
        thread.run();

        int i=Monitor.getMessages().size();
        boolean flag=Monitor.getMessages().get(i-1).contains("Ресурс обработан удачно");

        assertTrue("Результат не корректен",flag);
    }

    /**
     * Тестирование метода run() c ресурсами содержащими не кириллические символы
     * @throws Exception
     */
    @Test
    public void run_with_no_rus_sources() throws Exception{
        resForOpen = new FileForCheck(fileWithNoRus.getPath());
        thread = new UThread(threadGroup,resForOpen);
        thread.run();

        int i=Monitor.getAlarmMessages().size();
        boolean flag=Monitor.getAlarmMessages().get(i-1).contains("Слово не на кириллице");

        assertTrue("Результат не корректен",flag);
    }

    /**
     * Тестирование метода run() c ресурсами содержащими повторяющиеся слова
     * @throws Exception
     */
    @Test
    public void run_with_rep_words() throws Exception{
        int count=0;
        resForOpen = new FileForCheck(fileWithRepWords.getPath());
        thread = new UThread(threadGroup,resForOpen);
        thread.run();

        int i=Monitor.getAlarmMessages().size();
        boolean flag=Monitor.getAlarmMessages().get(i-1).contains("Повтор слова");

        assertTrue("Результат не корректен",flag);
    }

}