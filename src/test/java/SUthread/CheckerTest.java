package SUthread;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock.*;
import org.mockito.invocation.MockHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by raxis on 15.12.2016.
 */
public class CheckerTest {
    private static Logger logger = Logger.getLogger(Checker.class);

    @BeforeClass
    static public void beforeClass(){
        logger.info("before class");

    }
    private Checker checker;
    private List<String> stringList = new ArrayList<>();

    @Before
    public void before() throws Exception {
        this.checker = new Checker();
        this.stringList = new ArrayList<>();

    }

    @After
    public void after() throws Exception {

    }

    //проверка на текст без повторов
    @Test
    public void checkOnUnq() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("ехали медведи на велосипеде.");
        assertTrue("Ошибка! Метод должен вернуть null", checker.checkOnUnq(stringList)==null);
    }

    //проверка на текст с повторяющимися словами
    @Test
    public void checkOnUnq_with_repeatable_words() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("ехали медведи на велосипеде ехали.");
        assertTrue("Ошибка! Метод должен вернуть \"ехали\"", checker.checkOnUnq(stringList).equals("ехали"));
    }

    //проверка на выброс исключения
    /*@Test(expected = IOException.class)
    public void checkOnUnq_ExceptionTest() throws Exception {
        BufferedReader bReader = mock(BufferedReader.class);
        doThrow(new IOException()).when(bReader).readLine();

        checker.checkOnUnq(bReader);
    }*/

    //проверка: текст содержит только кириллицу
    @Test
    public void checkOnRus() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("ехали медведи на велосипеде.");

        assertTrue("Ошибка! Входные данны содержат только кириллицу, метод checkOnRus должен вернуть null",
                checker.checkOnRus(stringList)==null);
    }

    //проверка: текст содержит не только кириллицу
    @Test
    public void checkOnRus_with_non_cyr_words() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("ехали медведи на велосипеде. Stop.");

        assertTrue("Ошибка! Входные данные содержат только кириллицу, метод checkOnRus должен вернуть \"Stop\"",
                checker.checkOnRus(stringList)!=null);
    }

    //проверка на выброс исключения
    /*@Test(expected = IOException.class)
    public void checkOnRus_ExceptionTest() throws Exception {
        BufferedReader bReader = mock(BufferedReader.class);
        doThrow(new IOException()).when(bReader).readLine();

        checker.checkOnRus(bReader);
    }*/


}