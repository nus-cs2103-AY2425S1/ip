package knight2103.files;

/**
 * Indicates an exception that is caused by invalid contents in a file (such as invalid formatting).
 */
public class InvalidFileContentsException extends Exception {
    InvalidFileContentsException(String message) {
        super(message);
    }
}
