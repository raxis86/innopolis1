package SUthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raxis on 17.12.2016.
 * Служит для проверки списка строк
 * и поиска первого повторяющегося слова
 */
public class CheckerRep implements IChecker {
    private static final Logger logger = LoggerFactory.getLogger(CheckerRep.class);
    private Map<String,String> stringMap = new HashMap<>();

    /** Метод проверки списка строк на отсутствие повторяющихся слов
     * @param stringList - список проверяемых строк
     * @return String - первое слово, дважды встретившееся
     * в списке. Если такое слово не надено - возвращается null
     */
    @Override
    public String check(List<String> stringList) {
        logger.debug("check({})",stringList);
        for(String s:stringList){
            for(int i=0;i<s.split("[^\\p{L}]+").length;i++){
                String forPut = s.split("[^\\p{L}]+")[i].toLowerCase();
                logger.debug("forPut={}",forPut);
                if(stringMap.put(forPut,forPut)!=null){
                    logger.debug("stringMap.put({},{})!=null return true",forPut,forPut);
                    return forPut;
                }
            }
        }
        return null;
    }
}
