package slave;

/**
 * This exception is thrown when reading a save file with an incorrectly formatted string representation of a task
 */
public class InvalidSaveFileFormatException extends Exception {
    public InvalidSaveFileFormatException(String s) {
        super(s);
    }
}
