package bobby.exceptions;

public class EmptyDeadlineException extends BobbyException {
    public EmptyDeadlineException() {
        super("The description or deadline of a deadline task cannot be empty.");
    }
}
