public class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) throws TaskArgumentMissingException {
        super(description);
        if (from == null || from.isEmpty()) {
            throw new TaskArgumentMissingException(
                    """
                            Woah Woah! Calm down buddy!
                            Could you first tell when this event starts using '/from'?""");
        } else if (to == null || to.isEmpty()) {
            throw new TaskArgumentMissingException(
                    """
                            Dude, stop being overzealous! Surely this event doesn't last forever?
                            Think hard about it and you can tell me when it ends again with '/to'.""");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "Did you forget your EVENT?\nBecause you tried to make an event of nothing!";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
