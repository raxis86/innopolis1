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
 * Тесты для класса Monitors
 */
public class MonitorTest {
    private static Logger logger = Logger.getLogger(MonitorTest.class);

    private Monitor monitor;
    private List<Thread> threadList;
    private static final int quantityOfTestingThreads = 3;

    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"Monitor\"");

    }

    @Before
    public void before() throws Exception {
        this.threadList = new ArrayList<>();
        for(int i=0;i<quantityOfTestingThreads;i++){
            this.threadList.add(new Thread());
        }
        this.monitor=new Monitor(this.threadList);
    }

    /**
     * Тестируется, что метод start() запускает все потоки, переданные в
     * конструктор при создании объекта monitor
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        int count=0;
        monitor.start();
        for(Thread t:threadList){
            if(t.isAlive()) count++;
        }

        assertEquals(quantityOfTestingThreads,count);
    }

    /**
     * Все сообщения, переданные в этот статичесикй метод, должны возвратиться
     * статическим методом getMessages
     * @throws Exception
     */
    @Test
    public void process_and_getMessages() throws Exception {
        String str="";
        Monitor.process("msg1");
        Monitor.process("msg2");
        Monitor.process("msg3");

        for(String s:Monitor.getMessages()){
            str=str+s;
        }
        assertEquals("msg1msg2msg3",str);
    }

    /**
     * Все сообщения, переданные в этот статичесикй метод, должны возвратиться
     * статическим методом getAlarmMessages()
     * @throws Exception
     */
    @Test
    public void alarm_and_getAlarmMessages() throws Exception {
        String str="";
        Monitor.alarm("msg1");
        Monitor.alarm("msg2");
        Monitor.alarm("msg3");

        for(String s:Monitor.getAlarmMessages()){
            str=str+s;
        }
        assertEquals("msg1msg2msg3",str);
    }

    /**
     * Метод должен вернуть true если есть запущенные потоки
     * @throws Exception
     */
    @Test
    public void isAliveThreads() throws Exception {
        for(Thread t:threadList){
            t.start();
        }
        assertEquals(true,Monitor.isAliveThreads());
    }

    /**
     * Метод должен вернуть false если нет ни одного запущенного потока
     * @throws Exception
     */
    @Test
    public void isAleveThreads_with_no_alive_threads(){
        assertEquals(false,Monitor.isAliveThreads());
    }

}