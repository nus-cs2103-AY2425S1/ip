package joe.exceptions;

public class CorruptedFileException extends RuntimeException {

    /**
     * Constructor for CorruptedFileException.
     */
    public CorruptedFileException() {
        super("The file is corrupted");
    }
}
