package Task;

/**
 * Represents an error that occurs in the creation of a task.
 */
public class InvalidTaskException extends RasputinException {

    public InvalidTaskException(String message) {
        super(message);
    }
}
