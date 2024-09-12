package exception;
import java.lang.Exception;

/**
 * Uncategorized exceptions happened due to user input
 * */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
