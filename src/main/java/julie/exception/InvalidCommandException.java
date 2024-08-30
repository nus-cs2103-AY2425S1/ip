package julie.exception;

/**
 * An exception that is thrown when a command is not supported is called.
 */
public class InvalidCommandException extends JulieException {
    /**
     * Public constructor for the exception.
     * @param message The error message to be initialised.
     */
    public InvalidCommandException(String message) {
        super(String.format("Omg i'm so sorry I don't know what %s means", message));
    }
}
