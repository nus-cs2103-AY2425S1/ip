package jay.storage;

/**
 * Represents an exception thrown when there is an error in reading or writing data.
 */
public class DataIoException extends Exception {
    public DataIoException(String message) {
        super(message);
    }
}
