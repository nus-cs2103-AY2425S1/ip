public class EmptyDescException extends YapperException {
    public EmptyDescException(String message) {
        super("No task description given " + message);
    }
}
