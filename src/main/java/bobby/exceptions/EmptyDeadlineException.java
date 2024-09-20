package bobby.exceptions;

/**
 * The {@code EmptyDeadlineException} class represents a specific type of {@code BobbyException}
 * that is thrown when a deadline task is created with an empty description or deadline.
 */
public class EmptyDeadlineException extends BobbyException {

    /**
     * Constructs a new {@code EmptyDeadlineException} with a default detail message
     * indicating that the description or deadline of a deadline task cannot be empty.
     */
    public EmptyDeadlineException() {
        super("The description or deadline of a deadline task cannot be empty.");
    }
}
