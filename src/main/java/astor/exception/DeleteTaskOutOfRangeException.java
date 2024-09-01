package astor.exception;

public class DeleteTaskOutOfRangeException extends AstorException {
    public DeleteTaskOutOfRangeException(String message) {
        super(message);
    }

    public static DeleteTaskOutOfRangeException noTaskToDelete() {
        return new DeleteTaskOutOfRangeException("No astor.task to delete!");
    }

    public static DeleteTaskOutOfRangeException outOfRangeTaskToDelete(int size) {
        return new DeleteTaskOutOfRangeException("Please enter a valid astor.task number between 1 and " +
                size);
    }
}
