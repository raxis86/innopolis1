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
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        //logger.warn("some");

        /*List<String> stringList = new ArrayList<>();
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test1.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test2.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test3.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test4.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test5.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test6.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test7.txt");
        stringList.add("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test8.txt");

        Monitor monitor = new Monitor(stringList);*/


        List<Thread> threadList = new ArrayList<>();

        /*for(int i=0;i<2000;i++){
            threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test9.txt"));
        }*/

        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test1.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test2.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test3.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test4.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test5.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test6.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test7.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test8.txt"));
        threadList.add(new UThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test9.txt"));

        Monitor monitor = new Monitor(threadList);

        monitor.start();

        while (Monitor.isAliveThreads());

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
