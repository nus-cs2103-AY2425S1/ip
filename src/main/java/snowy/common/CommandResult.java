package snowy.common;

public class CommandResult {
    private final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedback() {
        return feedbackToUser;
    }
}
