package quack.exception;

/**
 * This exception class indicates that the user has input an invalid command.
 */
public class InvalidCommandException extends Exception {

    /**
     * Creates an InvalidCommandException exception object.
     * @param command The command retrived by the user.
     */
    public InvalidCommandException(String command) {

        super("I have no idea what you mean by " + command + ", please try another command");
    }
}
