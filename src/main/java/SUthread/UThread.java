package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raxis on 10.12.2016.
 * Класс описывающий логику потока-обработчика текста
 */
public class UThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(UThread.class);

    private String resource;
    private IReadable checkable;
    private ICheckable check;

    /**
     * Конструктор класса
     * @param resource - строка с путем у ресурсу
     * Создает объекты классов, реализующих IReadable и ICheckable в зависимости от ресурса
     */

    public UThread(String resource){
        super();
        this.resource=resource;
        if(resource.matches("^http.+$")){
            checkable = new UrlForCheck();
        }
        else {
            checkable = new FileForCheck();
        }
        check=new Checker();
    }

    /**
     * логика обработки ресурса
     */
    @Override
    public void run() {
        List<String> stringList = new ArrayList<>();
        try(BufferedReader bufferedReader = checkable.open(resource)) {
            while (!isInterrupted()){
                if(bufferedReader.ready()==false)break;
                stringList.add(bufferedReader.readLine());
            }
            if(isInterrupted()){
                logger.error(String.format("Обработка потока прервана %s", resource));
            }
            else{
                String nonCyr=check.checkOnRus(stringList);
                if(nonCyr!=null){
                    logger.error(String.format("Слово не на кириллице \"%s\" в ресурсе %s", nonCyr, resource));
                    Monitor.alarm(String.format("Слово не на кириллице \"%s\" в ресурсе %s", nonCyr, resource));
                }else
                {
                    if(isInterrupted()){
                        logger.error(String.format("Обработка потока прервана %s", resource));
                    }
                    else {
                        String str=check.checkOnUnq(stringList);
                        if(str!=null) {
                            logger.error(String.format("Повтор слова \"%s\" в ресурсе %s", str, resource));
                            Monitor.alarm(String.format("Повтор слова \"%s\" в ресурсе %s", str, resource));
                        }
                        else {
                            logger.info(String.format("%s: Ресурс обработан удачно %s",currentThread().getName(),resource));
                            Monitor.process(String.format("%s: Ресурс обработан удачно %s",currentThread().getName(),resource));
                        }
                    }
                }
            }

        }catch (IOException e) {
            logger.error("Ошибка в потоке, открывающем ресурс",e);
            //Monitor.alarm(e.getMessage());
            //e.printStackTrace();
        }

    }
}
