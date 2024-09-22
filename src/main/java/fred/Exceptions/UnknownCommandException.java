package fred.Exceptions;

/**
 * The UnknownCommandException is thrown when a user inputs a command
 * that is not recognized by the Fred application.
 */
public class UnknownCommandException extends FredException {

    /**
     * Constructs an UnknownCommandException with a default error message
     * indicating that the command is not recognized.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
