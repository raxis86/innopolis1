package SUthread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raxis on 11.12.2016.
 * Класс реализующий методы ICheckable
 */
public class Checker implements ICheckable {
    private Map<String,String> stringMap = new HashMap<>();
    private String resource;

    public Checker(){}

    public Checker(String resource){
        this.resource=resource;
    }

    /**
     *
     * @param stringList
     * @return
     */
    @Override
    public String checkOnUnq(List<String> stringList) {
        for(String s:stringList){
            for(int i=0;i<s.split("[^\\p{L}]+").length;i++){
                String forPut = s.split("[^\\p{L}]+")[i].toLowerCase();
                if(stringMap.put(forPut,forPut)!=null)return forPut;
            }
        }
        return null;
    }

    @Override
    public String checkOnRus(List<String> stringList) {
        for(String s:stringList){
            if(!s.matches("^[а-яА-ЯёЁ0-9\\d\\s\\p{Punct}]+$")) {
                for(int i=0;i<s.split("[^\\p{L}]+").length;i++) {
                    String nonCyr = s.split("[^\\p{L}]+")[i];
                    if (!nonCyr.matches("^[а-яА-ЯёЁ]+$")) {
                        return nonCyr;
                    }
                }
            }
        }
        return null;
    }

}
