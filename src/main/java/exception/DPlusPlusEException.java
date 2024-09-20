package exception;
import java.util.InputMismatchException;

/**
 * DukeException class is used to indicate exceptions in Duke.
 */
public class DPlusPlusEException extends InputMismatchException  {
    /**
     * Constructor for DukeException.
     * @param message The message to be displayed when the exception is thrown.
     */
    public DPlusPlusEException(String message) {
        super(message);
    }

}
