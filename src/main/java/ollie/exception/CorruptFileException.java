package ollie.exception;

/**
 * Represents an error for a corrupted file at the specified filepath.
 */
public class CorruptFileException extends OllieException {
    /**
     * Constructs an exception that represents a corrupted file.
     */
    public CorruptFileException(String filepath) {
        super("File in " + filepath + " is corrupted.");
    }
}
