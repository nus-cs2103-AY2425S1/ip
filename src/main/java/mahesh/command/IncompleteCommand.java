package mahesh.command;

/**
 * Represents a command that is incomplete or incorrect.
 * When executed, it returns an error message indicating the issue.
 */
public class IncompleteCommand extends Command {
    private final String errMessage;

    /**
     * Constructs an IncompleteCommand with the specified error message.
     *
     * @param errMessage the error message indicating the issue with the command
     */
    public IncompleteCommand(String errMessage) {
        this.errMessage = errMessage;
    }

    /**
     * Executes the IncompleteCommand by returning the error message.
     *
     * @return the error message indicating the issue with the command
     */
    @Override
    public String execute() {
        return this.errMessage;
    }
}