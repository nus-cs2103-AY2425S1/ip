package tars;

/**
 * This class handles the exception thrown for errors in Tars application
 * Extends Exception class which returns specific error messages based on invalid inputs or commands
 *
 * @author csk
 * @version 1
 */
public class TarsException extends Exception {
    public TarsException(String message) {
        super(message);
    }
}
