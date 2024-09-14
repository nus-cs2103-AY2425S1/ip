package exceptions;

/**
 * Represents a custom exception specific to the Duke application.
 * This exception is thrown when an error occurs that is unique
 * to the operations of Duke.
 */
public class EchoException extends Exception {
    /**
     * Create a DukeException without specific message.
     */
    public EchoException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Creates a DukeException with provided specific message.
     *
     * @param msg specific message of the exception.
     */
    public EchoException(String msg) {
        super(msg);
    }

    /**
     * Returns specific information of a DukeException.
     *
     * @return exception information.
     */
    @Override
    public String toString() {
        return "EchoException:" + this.getMessage();
    }
}
