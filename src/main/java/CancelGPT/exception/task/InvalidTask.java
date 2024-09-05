package CancelGPT.exception.task;

/**
 * Represents an Exception for invalid task.
 */
public class InvalidTask extends Exception {
    /**
     * Initialises InvalidTask with custom message for invalid task exception.
     */
    public InvalidTask(String message) {
        super(message);
    }
}
