package mryapper.exception;

/**
 * An exception thrown whenever the format of the data file is invalid
 * or there is no data file detected.
 */
public class InvalidFileDataException extends Exception {

    public InvalidFileDataException(String errorMessage) {
        super(errorMessage);
    }
}
