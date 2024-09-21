package jeff.exceptions;

/**
 * Represents an exception that is thrown when a file is detected to be corrupted.
 */
public class FileCorruptedException extends Exception {
    public FileCorruptedException(String message) {
        super(message);
    }
}
