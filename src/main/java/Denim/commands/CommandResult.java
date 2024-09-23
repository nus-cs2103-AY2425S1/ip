package denim.commands;

/**
 * Represents the result of executing a command.
 * This class encapsulates the reply or feedback to be shown to the user after a command is executed.
 */
public class CommandResult {
    private final String reply;
    private Command.CommandStatus status;

    /**
     * Constructs a CommandResult with the specified reply message.
     *
     * @param reply The reply message to be shown to the user.
     * @param status The outcome status as a result of executing the command.
     */
    public CommandResult(String reply, Command.CommandStatus status) {
        this.reply = reply;
        this.status = status;
    }

    /**
     * Returns the reply message contained in this CommandResult.
     *
     * @return The reply message.
     */
    public String getReply() {
        return reply;
    }

    /**
     * Returns the status of the CommandResult.
     *
     * @return the status after executing the command.
     */
    public Command.CommandStatus getStatus() {
        return status;
    }
}
