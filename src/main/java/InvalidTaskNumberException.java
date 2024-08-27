public class InvalidTaskNumberException extends OptimusExceptions{
    public InvalidTaskNumberException() {
        super("A Task with this number does not exist.");
    }

    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
