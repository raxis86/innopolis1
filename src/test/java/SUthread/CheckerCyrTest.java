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
 * Модульный тест для класса CheckerCyr
 */
public class CheckerCyrTest {
    private static Logger logger = Logger.getLogger(CheckerCyrTest.class);

    @BeforeClass
    static public void beforeClass(){
        logger.info("before class");

    }
    private IChecker checker;
    private List<String> stringList = new ArrayList<>();

    @Before
    public void before() throws Exception {
        this.checker = new CheckerCyr();
        this.stringList = new ArrayList<>();

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Тест на обработку методом check() текста только с кириллицей
     * @throws Exception
     */
    @Test
    public void check_with_only_cyr_words() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("Ехали медведи на велосипеде.");

        assertTrue("Error! Must return null",
                checker.check(stringList)==null);
    }

    /**
     * Тест на обработку методом check() текста c включением не кириллических символов
     * @throws Exception
     */
    @Test
    public void check_with_non_cyr_words() throws Exception{
        //List<String> stringList = new ArrayList<>();
        stringList.add("Ехали медведи на велосипеде. Stop.");

        assertTrue("Error! Must return \"Stop\"",
                checker.check(stringList)!=null);
    }

}