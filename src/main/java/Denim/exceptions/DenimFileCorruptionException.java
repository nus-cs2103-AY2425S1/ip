package denim.exceptions;

/**
 * Represents an exception specific to reading the denim.txt data file. This exception is thrown when
 * the file contains an unknown formatted text.
 */
public class DenimFileCorruptionException extends DenimException {
    public DenimFileCorruptionException(String message) {
        super(message);
    }
}
