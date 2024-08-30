package alex;

/**
 * Represents the Exception class thrown by Alex when an unexpected behaviour occurs
 */
public class AlexException extends Exception {
    public AlexException(String msg) {
        super(msg);
    }
}
