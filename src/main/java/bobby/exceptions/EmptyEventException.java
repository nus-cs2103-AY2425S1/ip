package bobby.exceptions;


/**
 * The {@code EmptyEventException} class represents a specific type of {@code BobbyException}
 * that is thrown when an event task is created with an empty description, start time, or end time.
 */
public class EmptyEventException extends BobbyException {

    /**
     * Constructs a new {@code EmptyEventException} with a default detail message
     * indicating that the description, start time, or end time of an event cannot be empty.
     */
    public EmptyEventException() {
        super("The description, start time, or end time of an event cannot be empty.");
    }
}
