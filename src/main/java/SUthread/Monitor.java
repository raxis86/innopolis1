package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by raxis on 15.12.2016.
 */
public class Monitor {
    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    //private static Lock lock = new ReentrantLock();
    private static List<String> messages = new ArrayList<>();
    private static List<String> alarmMsg = new ArrayList<>();
    private static List<Thread> threads = new ArrayList<>();

    private static void interruptAll(){
        for(Thread t:threads){
            t.interrupt();
        }
    }

    public Monitor(List<Thread> threads){
        this.threads=threads;
    }

    /*public Monitor(){

    }

    public void addThread(Thread thread){
        threads.add(thread);
    }*/


    public void start(){
        for(Thread t:threads){
            t.start();
        }
    }

    public static synchronized void process(String message){
            messages.add(message);
    }

    public static synchronized void alarm(String message) {
        if(!Thread.currentThread().isInterrupted()) {
            alarmMsg.add(message);
        }
        //alarmMsg.add(message);
            interruptAll();
    }

    public static List<String> getMessages(){
        return messages;
    }

    public static List<String> getAlarmMessages(){
        return alarmMsg;
    }

    public static boolean isAliveThreads(){
        for(Thread t:threads){
            if(t.isAlive())return true;
        }
        return false;
    }


}
