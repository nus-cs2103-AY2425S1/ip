package rainy.rainyexceptions;
import rainy.tasks.*;
import rainy.database.*;

/**
 *Occurs when user issues an marks a marked task or unmarks an unmarked task.
 */
public class InvalidMarkAndUnmarkException extends Exception{

    /**
     * Constructs a new <code>InvalidMarkAndUnmarkException</code> to be thrown.
     * @param errorMessage  Displays message when the exception is thrown.
     */
    public InvalidMarkAndUnmarkException(String errorMessage) {
        super(errorMessage);
    }
}