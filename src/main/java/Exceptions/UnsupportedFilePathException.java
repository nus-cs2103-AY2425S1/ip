package Exceptions;

/**
 * The UnsupportedFilePathException is thrown when an invalid or unsupported file path is encountered.
 * This exception is used to handle file path errors that prevent successful file operations.
 */
public class UnsupportedFilePathException extends Exception {

    /**
     * Constructs a new UnsupportedFilePathException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public UnsupportedFilePathException(String message) {
        super(message);
    }
}
