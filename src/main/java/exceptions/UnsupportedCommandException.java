package exceptions;

/**
 * The UnsupportedCommandException is thrown when an unsupported or unrecognized command is encountered.
 * This exception is used to handle invalid commands that the application does not recognize or support.
 */
public class UnsupportedCommandException extends Exception {

    /**
     * Constructs a new UnsupportedCommandException with the specified detail message.
     */
    public UnsupportedCommandException() {
        super("GO TO OUR GITHUB PAGE FOR THE LIST OF COMMANDS STOOPID!");
    }
}
