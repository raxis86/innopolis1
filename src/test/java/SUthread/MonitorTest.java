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
 * Тесты для класса Monitor
 */
public class MonitorTest {
    private static Logger logger = Logger.getLogger(MonitorTest.class);

    @BeforeClass
    public static void beforeClass() throws IOException {
        logger.info("Start tests for class \"Monitor\"");

    }

    @Before
    public void before() throws Exception {

    }


    /**
     * Все сообщения, переданные в этот метод, должны возвратиться
     * методом getMessages
     * @throws Exception
     */
    @Test
    public void process_and_getMessages() throws Exception {
        String str="";
        Monitor.process("msg1");
        Monitor.process("msg2");
        Monitor.process("msg3");

        for(int i=Monitor.getMessages().size()-3;i<Monitor.getMessages().size();i++)
        {
            str=str+Monitor.getMessages().get(i);
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

        for(int i=Monitor.getAlarmMessages().size()-3;i<Monitor.getAlarmMessages().size();i++)
        {
            str=str+Monitor.getAlarmMessages().get(i);
        }
        assertEquals("msg1msg2msg3",str);
    }

}