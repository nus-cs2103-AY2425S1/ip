package jeff.exception;

/**
 * Represents an exception for any file corruption.
 */
public class FileCorruptException extends Exception {
    private String message;

    /**
     * Constructor that creates a FileCorruptException object.
     */
    public FileCorruptException() {
        super();
        this.message = "Data file is corrupted!";
    }

    /**
     * Returns the message of the error.
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return this.message;
    }
}
