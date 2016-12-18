package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raxis on 17.12.2016.
 * Strategy-класс для динамического выбора чекера
 * Также имеет проверку на прерывание потока
 */
public class Checkering {
    private static final Logger logger = LoggerFactory.getLogger(Checkering.class);
    private IChecker checker;
    private String problemWord;

    /**
     * Конструктор класса
     * @param checker - класс, реализующий интерфейс IChecker
     */
    public Checkering(IChecker checker){
        logger.debug("Checkering({})",checker);
        this.checker=checker;
    }

    /**
     * Метод, для динамичесокго выбора класса
     * реализуещего интерфес IChecker
     * @param checker - класс, реализующий интерфейс IChecker
     */
    public void setChecker(IChecker checker){
        logger.debug("setChecker({})",checker);
        this.checker=checker;
    }

    /**
     * Метод, возвращающий слово, которое
     * вернул метод check
     * @return - проблемное слово
     */
    public String getProblemWord(){
        logger.debug("getProblemWord() return:{}",problemWord);
        return problemWord;
    }

    /**
     * Метод, соответствующий
     * выбранному классу
     * @param stringList - обрабатываемый список
     * @param sourceName - доп. параметр для информирования при исключении
     * @return String - проблемное слово
     * @throws SUSoftInterruptException
     */
    public String check(List<String> stringList, String sourceName) throws SUSoftInterruptException {
        logger.debug("check({},{})",stringList,sourceName);
        if(!Monitor.getAlarm()){
            problemWord=checker.check(stringList);
        }
        if(!Monitor.getAlarm()) {
            logger.debug("check() return:",problemWord);
            return problemWord;
        }
        else {
            throw new SUSoftInterruptException(String.format("Обработка ресурса прервана: %s", sourceName));
        }
    }
}
