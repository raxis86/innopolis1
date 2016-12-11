package SUthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raxis on 10.12.2016.
 * Класс описывающий логику потока-обработчика текста
 */
public class UThread implements Runnable {
    public static final Object monitor = new Object();
    public static List<Thread> threads = new ArrayList<>();
    private String resource="";
    private UReadable checkable;
    private UCheckable check;

    public static void addThread(String resource){
        threads.add(new Thread(new UThread(resource)));
    }

    public static void startAllThreads(){
        for(Thread t:threads){
            t.start();
        }
    }

    public UThread(String resource){
        this.resource=resource;
        if(resource.matches("^http.+$")){
            checkable = new UrlForCheck();
        }
        else {
            checkable = new FileForCheck(); //пока ресурс только файл
        }
        check=new UniqueCheck(resource);
    }

    @Override
    public void run() {
        try(BufferedReader bufferedReader = checkable.open(resource)) {
            String str=check.checkOnUnq(bufferedReader);
            if(str!=null){
                synchronized (monitor){
                    if(!Thread.currentThread().isInterrupted()){
                        System.out.println(String.format("Повтор слова \"%s\" в ресурсе %s",str,resource));
                        Thread.currentThread().getThreadGroup().interrupt();
                    }

                }
                return;
            }
        }catch (NoRusLungException e){
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(String.format("Ошибка в потоке, открывающем ресурс: %s",e.getMessage()));
            //e.printStackTrace();
        }
    }
}
