package sumode.exception;

/**
 * An Exception specific for SumoDE.
 */
public class SumoDException extends Exception {
    /**
    * Constructor for SumoDException Exception
    * @param message Message to be printed.
    */
    public SumoDException(String message) {
        super(message);
    }
}
