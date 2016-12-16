package SUthread;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by raxis on 11.12.2016.
 * Инткрфейс для чекера
 * Есть 2 метода для валидации
 */
public interface ICheckable {
    /**
     *
     * @param stringList
     * @return  - повторяющееся слово, иначе - null
     * @throws IOException
     */
    public String checkOnUnq(List<String> stringList);

    /**
     *
     * @param stringList
     * @return - слово не на кириллице, иначе - null
     * @throws IOException
     */
    public String checkOnRus(List<String> stringList);
}
