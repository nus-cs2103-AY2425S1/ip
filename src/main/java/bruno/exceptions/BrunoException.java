package bruno.exceptions;

/**
 * Most general exception, used to be inherited from by other exception classes.
 */
public class BrunoException extends Exception {
    public BrunoException(String message) {
        super(message);
    }
}
