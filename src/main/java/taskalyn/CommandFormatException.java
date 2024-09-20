package taskalyn;

/**
 * Represents an exception when a command is formatted incorrectly.
 */
public class CommandFormatException extends Exception {

    /**
     * Constructs a new CommandFormatException with a message depending on the command and format error.
     *
     * @param message Explanation for incorrect format.
     */
    public CommandFormatException(String message) {
        super(message);
    }
}
