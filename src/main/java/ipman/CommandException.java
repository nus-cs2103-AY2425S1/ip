package ipman;

/**
 * Custom exception for command errors
 *
 * @author miloaisdino
 */
public class CommandException extends Exception {
    public CommandException(String errorMessage) {
        super("Error: " + errorMessage);
    }
}
