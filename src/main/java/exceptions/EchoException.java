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
     * @param message specific message of the exception.
     */
    public EchoException(String message) {
        super(message + "\nYou can enter 'help' or 'help [command]' to get more information.");
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
