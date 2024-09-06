package maga;

/**
 * {@code InvalidCommandException} is a custom exception that is thrown when an invalid
 * command is input by the user. This exception is used in the {@code Parser} class
 * to handle unsupported commands.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super();
    }
}
