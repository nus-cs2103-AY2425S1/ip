package duke.exceptions;

public class InvalidEventException extends Exception {
    /**
     * Creates a duke.exceptions.InvalidEventException when the user enters an event with an invalid
     * `from` or `to` time.
     *
     * @param e description of the exception
     */
    public InvalidEventException(String e) {
        super(e);
    }
}
