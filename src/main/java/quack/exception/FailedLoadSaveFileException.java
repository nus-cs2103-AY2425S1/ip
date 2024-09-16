package quack.exception;

/**
 * This exception class indicates that the save file cannot be loaded.
 */
public class FailedLoadSaveFileException extends Exception {

    /**
     * Creates an FailedLoadSaveFileException exception object.
     */
    public FailedLoadSaveFileException() {
        super("I am unable to load the save file, "
            + "it could be corrupted or it has been deleted");
    }
}
