package duck.exceptions;

/**
 * Class representing errors when the supplied task index is out of bounds.
 */
public class TaskListIndexOutOfBoundsException extends Exception {
    private int indexSupplied;
    private int maxIndexAllowed;
    private UsageException usageException;

    /**
     * Constructor for TaskListIndexOutOfBoundsException.
     *
     * @param indexSupplied   Task list index supplied by the user.
     * @param maxIndexAllowed Largest index contained in task list.
     * @param usageException  The cause of this exception.
     */
    public TaskListIndexOutOfBoundsException(int indexSupplied, int maxIndexAllowed, UsageException usageException) {
        this.indexSupplied = indexSupplied;
        this.maxIndexAllowed = maxIndexAllowed;
        this.usageException = usageException;
    }

    @Override
    public String toString() {
        return String.format("%s\nIndex %s is out of bounds. Index should be an integer from 1 to %s (inclusive).",
                usageException.toString(), indexSupplied, maxIndexAllowed);
    }
}
