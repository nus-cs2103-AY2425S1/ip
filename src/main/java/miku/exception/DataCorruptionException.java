package miku.exception;

/**
 * Extends the exception class, thrown to indicate the data file is corrupted (wrong format)
 */
public class DataCorruptionException extends Exception {

    /**
     * Initialises a DataCorruptionException.
     * @param message the message to be added to the end of the warning..
     */
    public DataCorruptionException(String message) {
        super(message);
    }
}
