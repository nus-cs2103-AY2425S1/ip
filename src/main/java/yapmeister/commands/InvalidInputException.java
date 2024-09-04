package yapmeister.commands;

/**
 * Represents an Exception caused by an Invalid Input for commands
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
