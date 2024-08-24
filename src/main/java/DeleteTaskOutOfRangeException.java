public class DeleteTaskOutOfRangeException extends AstorException {
    public DeleteTaskOutOfRangeException(String message) {
        super(message);
    }

    public static DeleteTaskOutOfRangeException noTaskToDelete() {
        return new DeleteTaskOutOfRangeException("No task to delete!");
    }

    public static DeleteTaskOutOfRangeException outOfRangeTaskToDelete(int size) {
        return new DeleteTaskOutOfRangeException("Please enter a valid task number between 1 and " +
                size);
    }
}
