package dave.exceptions;

/**
 * The {@code InvalidDateTimeFormatException} class represents an exception that is thrown
 * when the user provides an incorrectly formatted date or time in the Dave application.
 */
public class InvalidDateTimeFormatException extends Exception {

    /**
     * Constructs an {@code InvalidDateTimeFormatException} with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
