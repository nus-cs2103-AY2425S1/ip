package exceptions;

/**
 * The UnsupportedFilePathException is thrown when an invalid or unsupported file path is encountered.
 * This exception is used to handle file path errors that prevent successful file operations.
 */
public class UnsupportedFilePathException extends Exception {

    /**
     * Constructs a new UnsupportedFilePathException with the specified detail message.
     */
    public UnsupportedFilePathException() {
        super("WRONG SAVE FILE!");
    }
}
