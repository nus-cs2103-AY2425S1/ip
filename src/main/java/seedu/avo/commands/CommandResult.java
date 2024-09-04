package seedu.avo.commands;

/**
 * Represents the result from command execution
 */
public class CommandResult {
    private final String message;
    public CommandResult(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
