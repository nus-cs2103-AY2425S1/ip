package opus.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isExit;

    /**
     * Creates a new command result.
     *
     * @param feedbackToUser The feedback to the user.
     * @param isExit Whether the program should exit.
     */
    public CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return isExit;
    }
}
