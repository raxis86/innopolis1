package SUthread;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raxis on 17.12.2016.
 * Модульный тест для класса CheckerRep
 */
public class CheckerRepTest {
    private static Logger logger = Logger.getLogger(CheckerRepTest.class);

    @BeforeClass
    static public void beforeClass(){
        logger.info("before class");

    }
    private IChecker checker;
    private List<String> stringList = new ArrayList<>();

    @Before
    public void before() throws Exception {
        this.checker = new CheckerRep();
        this.stringList = new ArrayList<>();

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Тест на обработку методом check() текста без повторов
     * @throws Exception
     */
    @Test
    public void check() throws Exception{
        stringList.add("Ехали медведи на велосипеде.");
        assertTrue("Error! Must return null", checker.check(stringList)==null);
    }

    /**
     * Тест на обработку методом checkOnUn() текста с повторами слов
     * @throws Exception
     */
    @Test
    public void check_with_repeatable_words() throws Exception{
        stringList.add("ехали медведи на велосипеде ехали.");
        assertTrue("Error! Must return \"ехали\"", checker.check(stringList).equals("ехали"));
    }

}