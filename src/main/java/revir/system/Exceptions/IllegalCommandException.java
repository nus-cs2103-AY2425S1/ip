package revir.system.Exceptions;

/**
 * Represents an exception that is thrown when an illegal command is encountered.
 * This exception is typically thrown when a command is not recognized or is not valid.
 */
public class IllegalCommandException extends Exception{
    /**
     * Constructs a new IllegalCommandException with the specified command.
     *
     * @param command the invalid command that caused the exception
     */
    public IllegalCommandException(String command) {
        super("\"" + command + "\" is not a valid command");
    }
}
