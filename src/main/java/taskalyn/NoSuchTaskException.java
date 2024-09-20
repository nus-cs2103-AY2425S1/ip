package taskalyn;

/**
 * Represents an exception thrown when a task does not exist.
 */
public class NoSuchTaskException extends Exception {

    /**
     * Constructs a new NoSuchTaskException with a message.
     *
     * @param message Message explaining that task does not exist.
     */
    public NoSuchTaskException(String message) {
        super(message);
    }
}
