package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raxis on 17.12.2016.
 * Служит для проверки списка строк
 * и поиска слова не на кириллице
 */
public class CheckerCyr implements IChecker {
    private static final Logger logger = LoggerFactory.getLogger(CheckerCyr.class);

    /** Метод проверки списка строк на отсутствие слов не на кириллице
     * @param stringList - список проверяемых строк
     * @return String - первое встретившееся слово не на кириллице
     * в списке. Если такое слово не надено - возвращается null
     */
    @Override
    public String check(List<String> stringList) {
        for(String s:stringList){
            if(!s.matches("^[а-яА-ЯёЁ0-9\\d\\s\\p{Punct}]+$")) {
                if(logger.isDebugEnabled()){
                    logger.debug("checkOnRus:s: \"" + s + "\"");
                }
                for(int i=0;i<s.split("[^\\p{L}]+").length;i++) {
                    String nonCyr = s.split("[^\\p{L}]+")[i];
                    if(logger.isDebugEnabled()){
                        logger.debug("checkOnRus:nonCyr: \"" + s + "\"");
                    }
                    if (!nonCyr.matches("^[а-яА-ЯёЁ]+$")) {
                        return nonCyr;
                    }
                }
            }
        }
        return null;
    }
}
