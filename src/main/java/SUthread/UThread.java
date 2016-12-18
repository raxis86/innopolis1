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

    private String sourceName;
    private IReadable source;

    private String interruptText;
    private String okText;

    /**
     * Конструктор класса
     * @param threadGroup - экземпляр ThreadGroup, к которой будет привязан поток
     * @param source - объект реализующий IReadable
     * Создает объекты классов, реализующих IReadable и ICheckable в зависимости от ресурса
     */

    public UThread(ThreadGroup threadGroup, IReadable source){
        super(threadGroup, "uthread" + source.getResourcePath().hashCode());

        this.sourceName = source.getResourcePath();
        this.source =source;

        logger.debug("UThread({},{})",threadGroup,source);
    }

    /**
     * логика обработки ресурса
     */
    @Override
    public void run() {

        try(BufferedReader bufferedReader = source.open()) {

            //Преобразование BufferedReader в список строк
            List<String> stringList = prepareStringListFromBufferReader(bufferedReader);

            //Создание экземпляра Strategy-класса. Передатся экземпляр чекера для поиска слов не на кириллице
            Checkering checkering = new Checkering(new CheckerCyr());

            //Если найдено не кириллическое слово
            if(checkering.check(stringList,sourceName)!=null){
                synchronized (Monitor.monitor){
                    Monitor.alarm("Слово не на кириллице \""+ checkering.getProblemWord() +"\". " + sourceName);
                }
                logger.error("Слово не на кириллице \""+ checkering.getProblemWord() +"\". " + sourceName);
            }

            //Передается экземпляр класса, реализующий поиск повторяющихся слов
            checkering.setChecker(new CheckerRep());

            //Если найдено повторяющееся слово
            if(checkering.check(stringList,sourceName)!=null){
                synchronized (Monitor.monitor){
                    Monitor.alarm("Повтор слова \""+ checkering.getProblemWord() + "\". "+ sourceName);
                }
                logger.error("Повтор слова \""+ checkering.getProblemWord() + "\". "+ sourceName);
            }

            //Повторяющихся слов не нашлось, следовательно ресурс обработан удачно
            else{
                synchronized (Monitor.monitor){
                    Monitor.process(String.format("%s: Ресурс обработан удачно %s",currentThread().getName(), sourceName));
                }
                logger.info(String.format("%s: Ресурс обработан удачно %s",currentThread().getName(), sourceName));
            }

        }catch(SUSoftInterruptException e){
            logger.error(e.getMessage());

        }catch(IOException e) {
            logger.error("Ошибка при открытии ресурса",e);
        }

    }

    /**
     * Метод для преобразовния BufferedReader в список строк
     * @param bufferedReader
     * @return Список строк
     * @throws IOException
     * @throws SUSoftInterruptException - выкидывается, если поток прерван
     */
    private List<String> prepareStringListFromBufferReader(BufferedReader bufferedReader) throws IOException, SUSoftInterruptException {
        logger.debug("prepareStringListFromBufferReader({})",bufferedReader);
        List<String> stringList = new ArrayList<>();
        while (!Monitor.getAlarm()){
            if(bufferedReader.ready()==false)break;
            stringList.add(bufferedReader.readLine());
        }
        if(Monitor.getAlarm()){
            throw new SUSoftInterruptException(String.format("Обработка ресурса прервана: %s", sourceName));
        }
        logger.debug("prepareStringListFromBufferReader(): return: {}",stringList);
        return stringList;
    }

}
