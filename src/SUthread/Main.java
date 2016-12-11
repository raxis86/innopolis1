package SUthread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raxis on 10.12.2016.
 */
public class Main {
    public static void main(String[] args) {

        UThread.addThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test.txt");
        UThread.addThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test1.txt");
        UThread.addThread("C:\\Work\\Java\\Innopolis\\SeekUniq\\Test2.txt");
        UThread.addThread("https://www.gazeta.ru/export_news.shtml");

        UThread.startAllThreads();

    }
}
