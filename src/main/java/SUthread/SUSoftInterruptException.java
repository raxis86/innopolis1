package SUthread;

/**
 * Created by raxis on 17.12.2016.
 * Исключение для организации прерывания работы потоков
 */
public class SUSoftInterruptException extends Exception {
    public SUSoftInterruptException(String message){
        super(message);
    }
}
