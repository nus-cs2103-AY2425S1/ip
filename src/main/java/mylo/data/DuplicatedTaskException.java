package mylo.data;

/**
 * Exception thrown to indicate that a task with identical attributes
 * already exists in the task list.
 * <p>This exception is used to prevent the addition of duplicate tasks.</p>
 *
 * @author cweijin
 */
public class DuplicatedTaskException extends Exception {
    /**
     * Constructs a {@code DuplicatedTaskException} with the specified detail message.
     *
     * @param message The detail message, which provides more information about the duplication error.
     */
    public DuplicatedTaskException(String message) {
        super(message);
    }
}
