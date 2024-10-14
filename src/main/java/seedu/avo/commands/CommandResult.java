package seedu.avo.commands;

/**
 * Represents the result from command execution
 */
public class CommandResult {
    private final String message;
    private final boolean isExit;

    /**
     * @param message String result of command
     * @param isExit Status to check whether to if command is an ExitCommand
     */
    public CommandResult(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }
    public String getMessage() {
        return message;
    }
    public boolean getExitStatus() {
        return isExit;
    }
}
