package gojou;

/**
 * Represents the Exception class thrown by Gojou when an unexpected behaviour occurs
 */
public class GojouException extends Exception {
    public GojouException(String msg) {
        super(msg);
    }
}
