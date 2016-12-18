package SUthread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raxis on 10.12.2016.
 * Параметры, переданные в метод main - файловые ресурсы
 * Создается список потоков, все потоки запускаются
 * Результат обработки выводится в консоль
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("main({}) started!",args);

        List<Thread> threadList = new ArrayList<>();
        ThreadGroup threadGroup = new ThreadGroup("Group");

        /*for(int i=1;i<10;i++){
            threadList.add(new UThread(threadGroup, new FileForCheck(String.format("Test%d.txt",i))));
        }*/

        for(int i=0;i<args.length;i++){
            threadList.add(new UThread(threadGroup, new FileForCheck(args[i])));
        }


        for(Thread t:threadList){
            t.start();
        }

        while (threadGroup.activeCount()>0){
            //ждем, пока завершатся все потоки
        }

        for(String s:Monitor.getAlarmMessages()){
            System.out.println(s);
        }

        System.out.println("\nИнформационно:");

        int i=0;
        for(String s:Monitor.getMessages()){
            i++;
            System.out.println(i + ":" + s);
        }
    }
}
