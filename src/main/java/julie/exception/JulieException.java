package julie.exception;

/**
 * An abstract class that represents the possible exceptions from the Chat Bot.
 */
public abstract class JulieException extends Exception {
    /**
     * Public constructor for a JulieException
     * @param message The error message to be added.
     */
    public JulieException(String message) {
        super(message);
    }
}
