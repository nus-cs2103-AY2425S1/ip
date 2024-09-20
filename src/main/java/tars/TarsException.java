package tars;

/**
 * Acts as the class Handling the exception thrown for errors in Tars application
 * Extends Exception class which returns specific error messages based on invalid inputs or commands
 *
 * @author csk
 */
public class TarsException extends Exception {
    /**
     * Constructs the tars exception object which takes in an error message
     * @param message
     */
    public TarsException(String message) {
        super(message);
    }
}
