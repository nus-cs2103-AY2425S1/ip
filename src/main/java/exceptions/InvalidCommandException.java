package exceptions;

/**
 * Represents a class for an Exception where invalid command is given.
 */
public class InvalidCommandException extends Exception {

    /**
     * Represents a constructor for an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Let's go inputting valid commands only \nLet's go type 'help' for valid commands");
    }
}
