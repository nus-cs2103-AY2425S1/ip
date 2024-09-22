package hoodini;
/**
 * Throws an exception when
 * there is an invalid task
 */
public class HandleException extends Exception {
    public HandleException(String message) {
        super(message);
    }
}
