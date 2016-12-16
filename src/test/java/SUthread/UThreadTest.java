package SUthread;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raxis on 16.12.2016.
 */
public class UThreadTest {
    private static Logger logger = Logger.getLogger(UThreadTest.class);

    private List<Thread> threadList = new ArrayList<>();
    private UThread thread;
    private File file, fileWithNoRus, fileWithRepWords;
    private BufferedWriter bufferedWriter;
    private final String testText = "Ехали медведи на велосипеде";
    private final String testTextRep = "Ехали медведи на велосипеде медведи";
    private final String testTextNoRus = "Ехали медведи на велосипеде. Stop.";

    private static boolean isAliveThreads(List<Thread> threads){
        for(Thread t:threads){
            if(t.isAlive())return true;
        }
        return false;
    }

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"UThreadTest\"");

    }

    @Before
    public void before() throws Exception {
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
    }

    /**
     * Тестирование метода run() c правильными ресурсами
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        thread = new UThread(file.getPath());
        thread.run();
        /*int count=0;
        for(int i=0;i<1;i++){
            threadList.add(new UThread(file.getPath()));
        }
        for(Thread t:threadList){
            t.run();
        }

        while (isAliveThreads(threadList));

        for(int i=0;i<1;i++){
            if(Monitor.getMessages().get(i).contains("Ресурс обработан удачно")) count++;
        }

        assertEquals(1,count);*/
        int count=0;
        for(int i=0;i<Monitor.getMessages().size();i++){
            if(Monitor.getMessages().get(i).contains("Ресурс обработан удачно")) count++;
        }

        assertTrue("Результат не корректен",count>0);
    }

    /**
     * Тестирование метода run() c ресурсами содержащими не кириллические символы
     * @throws Exception
     */
    @Test
    public void run_with_no_rus_sources() throws Exception{
        /*int count=0;
        for(int i=0;i<1;i++){
            threadList.add(new UThread(fileWithNoRus.getPath()));
        }
        for(Thread t:threadList){
            t.run();
        }

        while (isAliveThreads(threadList));

        for(int i=0;i<1;i++){
            if(Monitor.getAlarmMessages().get(i).contains("Слово не на кириллице")) count++;
        }

        assertEquals(1,count);*/
        int count=0;
        thread = new UThread(fileWithNoRus.getPath());
        thread.run();

        for(int i=0;i<Monitor.getAlarmMessages().size();i++){
            if(Monitor.getAlarmMessages().get(i).contains("Слово не на кириллице")) count++;
        }

        assertTrue("Результат не корректен",count>0);
    }

    /**
     * Тестирование метода run() c ресурсами содержащими повторяющиеся слова
     * @throws Exception
     */
    @Test
    public void run_with_rep_words() throws Exception{
        /*int count=0;
        for(int i=0;i<1;i++){
            threadList.add(new UThread(fileWithRepWords.getPath()));
        }
        for(Thread t:threadList){
            t.run();
        }

        while (isAliveThreads(threadList));

        for(int i=0;i<1;i++){
            if(Monitor.getAlarmMessages().get(i).contains("Повтор слова")) count++;
        }

        assertEquals(1,count);*/
        int count=0;
        thread = new UThread(fileWithRepWords.getPath());
        thread.run();

        for(int i=0;i<Monitor.getAlarmMessages().size();i++){
            if(Monitor.getAlarmMessages().get(i).contains("Повтор слова")) count++;
        }

        assertTrue("Результат не корректен",count>0);
    }

}