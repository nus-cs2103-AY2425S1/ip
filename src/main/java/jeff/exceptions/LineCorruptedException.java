package jeff.exceptions;

/**
 * Represents an exception that is thrown when a specific line in a file is detected to be corrupted.
 * @see FileCorruptedException
 */
public class LineCorruptedException extends FileCorruptedException {
    public LineCorruptedException(String message) {
        super(message);
    }
}
