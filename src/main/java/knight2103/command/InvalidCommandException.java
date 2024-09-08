package knight2103.command;

/**
 * Indicates an exception that is caused by invalid Commands executed.
 */
public class InvalidCommandException extends Exception {
    InvalidCommandException(String message) {
        super(message);
    }
}
