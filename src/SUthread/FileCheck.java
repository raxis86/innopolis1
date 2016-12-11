package SUthread;

import java.io.*;
import java.util.*;

/**
 * Created by raxis on 10.12.2016.
 * Класс для открытия и обработки тектового файла
 */
public class FileCheck implements UCheckable{
    private Map<String,String> stringMap = new HashMap<>();
    private FileInputStream fileInputStream;
    private InputStreamReader fileReader;
    private String resource;

    @Override
    public BufferedReader open(String resource) throws UnsupportedEncodingException, FileNotFoundException {
        fileInputStream = new FileInputStream(resource);
        fileReader = new InputStreamReader(fileInputStream,"Cp1251");
        this.resource=resource;
        return new BufferedReader(fileReader);
    }

    @Override
    public String checkOnUnq(BufferedReader reader) throws IOException {
        while (!Thread.currentThread().isInterrupted()){
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("was interrupted");
                return null;
            }*/
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

    @Override
    public void close() throws IOException {
        fileInputStream.close();
        fileReader.close();
    }
}
