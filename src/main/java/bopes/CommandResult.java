package bopes;

public class CommandResult {
    private final String feedbackToUser;
    private final boolean isError;

    public CommandResult(String feedbackToUser, boolean isError) {
        this.feedbackToUser = feedbackToUser;
        this.isError = isError;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isError() {
        return isError;
    }
}
