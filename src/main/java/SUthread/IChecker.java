package SUthread;

import java.util.List;

/**
 * Created by raxis on 17.12.2016.
 * Интерфейс для чекера
 */
public interface IChecker {
    /**
     * Объявляет метод, который должен осуществлять
     * проверку списка строк и находить некоторое
     * проблемное слово
     * @param stringList - список проверяемых строк
     * @return  - проблемное слово, иначе - null
     */
    public String check(List<String> stringList);
}
