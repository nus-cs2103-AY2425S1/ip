package stelle.exception;

/**
 * Exception thrown when something goes wrong while writing data to local file.
 * Child of stelle.exception.StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class StorageWriteException extends StelleException {
    public StorageWriteException() {
        super("Something went wrong when writing to file!");
    }
}

