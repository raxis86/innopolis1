package SUthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raxis on 11.12.2016.
 * Проверка слова на уникальность
 */
public class UniqueCheck implements UCheckable {
    private Map<String,String> stringMap = new HashMap<>();
    private BufferedReader reader;
    private String resource="не указан";

    public UniqueCheck(){}

    public UniqueCheck(String resource){
        this.resource=resource;
    }

    @Override
    public String checkOnUnq(BufferedReader reader) throws IOException {
        this.reader=reader;
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("was interrupted");
                return null;
            }
            String line = reader.readLine();
            if(line==null) break;
            if(line.matches("^[а-яА-ЯёЁ0-9\\d\\s\\p{Punct}]+$")){ //Проверка строки на кириллицу
                for(int i=0;i<line.split("[^\\p{L}]+").length;i++){
                    String forPut = line.split("[^\\p{L}]+")[i].toLowerCase();
                    if(stringMap.put(forPut,forPut)!=null)return forPut;
                }
            }else {
                throw new NoRusLungException(resource); //в строке нашлись слова/буквы не на кириллице
            }

        }
        return null;
    }

}
