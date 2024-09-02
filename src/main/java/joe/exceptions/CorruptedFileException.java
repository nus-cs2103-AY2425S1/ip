package joe.exceptions;

/**
 * Represents an exception thrown when the file is corrupted.
 */
public class CorruptedFileException extends RuntimeException {

    /**
     * Constructor for CorruptedFileException.
     */
    public CorruptedFileException() {
        super("The file is corrupted");
    }
}
