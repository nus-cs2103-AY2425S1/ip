package screwllum.exception;

/**
 * An Exception specific to the Screwllum bot for commands that do not follow the required format.
 */
public class InvalidCommandException extends ScrewllumException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
