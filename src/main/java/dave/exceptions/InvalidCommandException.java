package dave.exceptions;

/**
 * The {@code InvalidCommandException} class represents an exception that is thrown
 * when the user inputs an unrecognized or invalid command in the Dave application.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an {@code InvalidCommandException} with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
