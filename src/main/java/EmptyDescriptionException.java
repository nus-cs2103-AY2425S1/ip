public class EmptyDescriptionException extends NuggetException {
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }
}
