package botty.exceptions;

/**
 * Exception thrown when task string is invalid
 */
public class CorruptedTaskStringException extends BottyException {
    /**
     * Constructs a new {@code CorruptedTaskString}.
     */
    public CorruptedTaskStringException() {
        super("Task string is corrupted! Failed to retrieve task");
    }
}
