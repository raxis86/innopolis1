package SUthread;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

/**
 * Created by raxis on 17.12.2016.
 * Модульный тест для класса Checkering
 */
public class CheckeringTest {
    private static Logger logger = Logger.getLogger(CheckerCyrTest.class);

    @BeforeClass
    static public void beforeClass(){
        logger.info("before class");

    }
    private Checkering checkering;
    private List<String> stringList = new ArrayList<>();

    @Before
    public void before() throws Exception {
        this.stringList = new ArrayList<>();
        this.stringList.add("Hello my friend!");

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
     * Проверка, что метод setChecker
     * изменяет поведение метода check()
     * @throws Exception
     */
    @Test
    public void setChecker() throws Exception {
        checkering = new Checkering(new CheckerRep());
        String word1=checkering.check(stringList,"");
        checkering.setChecker(new CheckerCyr());
        String word2=checkering.check(stringList,"");

        assertNotEquals("Must be not equals!",word1,word2);
    }

    /**
     * Метод getProblemWord() должен вернуть слово, полученное методом check()
     * @throws Exception
     */
    @Test
    public void getProblemWord() throws Exception {
        checkering=new Checkering(new CheckerCyr());
        String word1=checkering.check(stringList,"");
        String word2=checkering.getProblemWord();

        assertEquals(word1,word2);
    }

    /**
     * Метод check() должен соответствовать, переданному в конструктор объекту
     * @throws Exception
     */
    @Test
    public void check() throws Exception {
        CheckerCyr checkerCyr = new CheckerCyr();
        checkering=new Checkering(checkerCyr);
        String word1 = checkering.check(stringList,"");
        String word2 = checkerCyr.check(stringList);

        assertEquals(word1,word2);
    }

    /**
     * Проверка, что метод check() бросает исключение
     * при выставлении флага монитора alarm в true
     * @throws Exception
     */
    @Test(expected = SUSoftInterruptException.class)
    public void check_throw_SUSoftInterrupt_Exception() throws Exception{
        Class c = Monitor.class;
        Field[] fields = c.getDeclaredFields();
        for(Field field:fields){
            if("alarm".equals(field.getName())){
                field.setAccessible(true);
                field.setBoolean(c,true);
            }
        }

        checkering=new Checkering(new CheckerCyr());

        //doThrow(new SUSoftInterruptException("")).when(checkering).check(stringList,"");

        checkering.check(stringList,"");
    }

}