package hoodini;
/**
 * Exception class to throw exception when
 * there is an invalid task
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}
