package denim.commands;

/**
 * Represents the result of executing a command.
 * This class encapsulates the reply or feedback to be shown to the user after a command is executed.
 */
public class CommandResult {
    private final String reply;

    /**
     * Constructs a CommandResult with the specified reply message.
     *
     * @param reply The reply message to be shown to the user.
     */
    public CommandResult(String reply) {
        this.reply = reply;
    }

    /**
     * Returns the reply message contained in this CommandResult.
     *
     * @return The reply message.
     */
    public String getReply() {
        return reply;
    }

}
