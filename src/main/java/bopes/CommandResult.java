package bopes;

/**
 * Represents the result of a command execution.
 * Contains feedback to the user and a flag indicating if the command resulted in an error.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isError;

    /**
     * Constructs a {@code CommandResult} with the specified feedback and error status.
     *
     * @param feedbackToUser the feedback message to be shown to the user
     * @param isError        indicates whether the command execution resulted in an error
     */
    public CommandResult(String feedbackToUser, boolean isError) {
        this.feedbackToUser = feedbackToUser;
        this.isError = isError;
    }

    /**
     * Returns the feedback message to the user.
     *
     * @return the feedback message
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if the command execution resulted in an error.
     *
     * @return true if there was an error; false otherwise
     */
    public boolean isError() {
        return isError;
    }
}
