package exceptions;

/**
 * Represents a class for an Exception where user tries to operate on a non-existent task.
 */
public class NonExistentTaskException extends Exception {

    /**
     * Represents a constructor for a NonExistentTaskException
     */
    public NonExistentTaskException() {
        super("Task does not exist");
    }
}
