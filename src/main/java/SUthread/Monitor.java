package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by raxis on 15.12.2016.
 * Класс, содержащий монитор для синхронизации потоков,
 * а также переменные для хранения сообщений потоков и
 * переменную-флаг для прерывания потоков
 */
public class Monitor {
    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    /**
     * monitor - монитор для захвата потоками
     * alarm - флаг для прерывания работы потоков
     */
    public static final Object monitor = new Object();
    private static volatile boolean alarm=false;
    private static volatile List<String> messages = new ArrayList<>();
    private static volatile List<String> alarmMsg = new ArrayList<>();

    /**
     * Getter переменной alarm
     * @return значение переменной alarm
     */
    public static boolean getAlarm() {
        return alarm;
    }

    /**
     * Метод для записи сообщений
     * об удачной обработке ресурса от потоков
     * @param message - сообщение
     */
    public static void process(String message){
        messages.add(message);
    }

    /**
     * Метод для записи сообщений
     * об неудачной обработке ресурса от потоков
     * и выставления флага alarm в true
     * @param message - сообщение
     */
    public static void alarm(String message) {
        alarm=true;
        alarmMsg.add(message);
    }

    /**
     * Метод, возвращающий список сообщений о корректной обработке
     */
    public static List<String> getMessages(){
        return messages;
    }

    /**
     * Метод, возвращающий список сообщений о некорректной обработке
     */
    public static List<String> getAlarmMessages(){
        return alarmMsg;
    }


}
