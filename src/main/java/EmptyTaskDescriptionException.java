public class EmptyTaskDescriptionException extends FredException {
    public EmptyTaskDescriptionException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
