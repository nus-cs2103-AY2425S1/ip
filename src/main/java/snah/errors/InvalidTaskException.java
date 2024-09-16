package snah.errors;

/**
 * Exception for invalid task index
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(int taskIndex) {
        super(String.format("Task %d does not exist", taskIndex));
    }

}
