package tecna.exception;

/**
 * Indicates errors when working with files.
 *
 * @author DennieDan.
 */
public class TecnaStorageException extends Exception {
    /**
     * Constructs a TecnaStorageException instance.
     * @param message Details of the error.
     */
    public TecnaStorageException(String message) {
        super(message);
    }
}
