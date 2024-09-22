package parser;

/**
 * An exception that signifies invalid input from user.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(InvalidCommands invalidCommands) {
        super(invalidCommands.getErrorMessage());
    }
}
