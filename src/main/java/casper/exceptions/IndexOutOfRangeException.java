package casper.exceptions;

/**
 * Thrown to indicate that a task index is out of range.
 * This exception provides a suggested solution for providing a valid index.
 */
public class IndexOutOfRangeException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code IndexOutOfRangeException} with a default message and solution.
     * The exception message indicates that the task index is out of range, and the solution
     * provides guidance on how to specify a valid index and how to list all tasks.
     */
    public IndexOutOfRangeException() {
        super("Task index out of range.",
                "Provide a valid index corresponding to a task. "
                        + "You may use the `list` command to see all tasks.");

    }
}
