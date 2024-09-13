package astor.exception;

/**
 * Represents an exception where the task index given is out of valid range.
 */
public class DeleteTaskOutOfRangeException extends AstorException {
    public DeleteTaskOutOfRangeException(String message) {
        super(message);
    }

    public static DeleteTaskOutOfRangeException noTaskToDelete() {
        return new DeleteTaskOutOfRangeException("No task to delete!");
    }

    /**
     * Creates a {@code DeleteTaskOutOfRangeException} with a message indicating that
     * the task number is out of range.
     *
     * @param size the maximum valid task number
     * @return a new {@code DeleteTaskOutOfRangeException} with a relevant message
     */
    public static DeleteTaskOutOfRangeException outOfRangeTaskToDelete(int size) {
        return new DeleteTaskOutOfRangeException("Please enter a valid task number between 1 and "
                + size);
    }
}
