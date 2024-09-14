package fred.Exceptions;

public class EmptyTaskDescriptionException extends FredException {
    public EmptyTaskDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }
}
