package snowy.common;

/**
 * Represents the result of executing a command.
 *
 * The CommandResult class encapsulates the feedback message that will be displayed to the user after they have
 * executed a command. It provides methods to retrieve the message. It extends the Command class but only returns
 * the result of other command execution.
 */
public class CommandResult extends Command{
    private final String feedbackToUser;

    /**
     * Constructs a CommandResult with the specified feedback message.
     *
     * @param feedbackToUser the feedback message to be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Returns the feedback message intended for the user.
     *
     * @return the feedback message as a string
     */
    public String getFeedback() {
        return feedbackToUser;
    }
}
