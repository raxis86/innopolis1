package SUthread;

import java.io.IOException;

/**
 * Created by raxis on 11.12.2016.
 */
public class NoRusLungException extends IOException {
    public NoRusLungException(String resource) {
        super("Ресурс содержит не кириллические символы: " + resource);
    }
}
