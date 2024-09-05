package jay.task;

/**
 * Represents an exception when an invalid task is encountered.
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}
