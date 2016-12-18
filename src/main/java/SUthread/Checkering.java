package SUthread;

import java.util.List;

/**
 * Created by raxis on 17.12.2016.
 * Strategy-класс для динамического выбора чекера
 * Также имеет проверку на прерывание потока
 */
public class Checkering {
    private IChecker checker;
    private String problemWord;

    /**
     * Конструктор класса
     * @param checker - класс, реализующий интерфейс IChecker
     */
    public Checkering(IChecker checker){
        this.checker=checker;
    }

    /**
     * Метод, для динамичесокго выбора класса
     * реализуещего интерфес IChecker
     * @param checker - класс, реализующий интерфейс IChecker
     */
    public void setChecker(IChecker checker){
        this.checker=checker;
    }

    /**
     * Метод, возвращающий слово, которое
     * вернул метод check
     * @return - проблемное слово
     */
    public String getProblemWord(){
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
        if(!Monitor.getAlarm()){
            problemWord=checker.check(stringList);
        }
        if(!Monitor.getAlarm()) {
            return problemWord;
        }
        else {
            throw new SUSoftInterruptException(String.format("Обработка ресурса прервана: %s", sourceName));
        }
    }
}
