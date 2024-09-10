package exceptions;

/**
 * The {@code InvalidFileFormatException} class represents a custom exception that is thrown
 * in the application when the file is unable to be read properly.
 * It extends the {@code Exception} class, allowing it to be used as a checked exception.
 */
public class InvalidFileFormatException extends Exception {

    /**
     * Constructs a new {@code InvalidFileFormatException} with the specified detail message.
     *
     * @param message The detail message, which provides more information about the error that occurred.
     */
    public InvalidFileFormatException(String message) {
        super(message);
    }

}
