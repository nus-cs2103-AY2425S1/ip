package astor.exception;

/**
 * Represents an exception where the task index given is out of valid range.
 */
public class DeleteTaskOutOfRangeException extends AstorException {
    public DeleteTaskOutOfRangeException(String message) {
        super(message);
    }

    public static DeleteTaskOutOfRangeException noTaskToDelete() {
        return new DeleteTaskOutOfRangeException("No ask to delete!");
    }

    public static DeleteTaskOutOfRangeException outOfRangeTaskToDelete(int size) {
        return new DeleteTaskOutOfRangeException("Please enter a valid task number between 1 and "
                + size);
    }
}
