package hoodini;
/**
 * Exception class to throw exception when
 * there is an invalid task
 */
public class HandleException extends Exception {
    public HandleException(String message) {
        super(message);
    }
}
