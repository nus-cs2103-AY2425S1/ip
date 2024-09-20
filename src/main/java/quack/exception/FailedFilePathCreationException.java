package quack.exception;

/**
 * This exception class indicates that the save file folder cannot be created.
 */
public class FailedFilePathCreationException extends Exception {

    /**
     * Creates an FailedFilePathCreationException exception object.
     */
    public FailedFilePathCreationException() {
        super("I am unable to create a folder for the save file.");
    }
}
