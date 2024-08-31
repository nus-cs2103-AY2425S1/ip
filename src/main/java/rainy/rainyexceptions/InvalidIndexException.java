package rainy.rainyexceptions;
import rainy.database.*;
import rainy.tasks.*;

/**
 *Occurs when user issues an invalid task number.
 */
public class InvalidIndexException extends Exception {

    /**
     * Constructs a new <code>InvalidIndexException</code> to be thrown.
     * @param errorMessage  Displays message when the exception is thrown.
     */
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}