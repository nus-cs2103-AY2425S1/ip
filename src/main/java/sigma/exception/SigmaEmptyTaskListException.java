package sigma.exception;

/**
 * The {@code SigmaEmptyTaskListException} is thrown when an operation is attempted
 * on an empty task list.
 */
public class SigmaEmptyTaskListException extends SigmaException {
    /**
     * Returns a string representation of the exception, including the
     * base exception message and an indication that the task list is empty.
     *
     * @return A string message indicating the task list is empty.
     */
    @Override
    public String toString() {
        return String.format("%s Task list is empty.", super.toString());
    }
}
