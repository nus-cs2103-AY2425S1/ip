package duke.exceptions;

public class UnknownMessageException extends Exception {
    /**
     * Creates a duke.exceptions.UnknownMessageException when the user enters a message that is not a task.
     *
     * @param e: description of the exception
     */
    public UnknownMessageException() {}
    public UnknownMessageException(String e) {
        super(e);
    }
}
