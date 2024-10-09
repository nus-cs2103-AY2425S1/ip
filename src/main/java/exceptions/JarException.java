package exceptions;

/**
 * Represents an exception specific to Jar Bot.
 * This exception is thrown when the bot encounters an error that is specific to its operations.
 */
public class JarException extends Exception {

    /**
     * Constructs a new JarException with the specified detail message.
     *
     * @param message the detail message.
     */
    public JarException(String message) {
        super(message);
    }
}
