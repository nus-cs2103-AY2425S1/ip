package dave.exceptions;

/**
 * The {@code InvalidDescriptionException} class represents an exception that is thrown
 * when the user provides an invalid or incomplete description for a task in the Dave application.
 */
public class InvalidDescriptionException extends Exception {

    /**
     * Constructs an {@code InvalidDescriptionException} with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}

