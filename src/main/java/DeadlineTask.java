public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String description, String deadline) throws TaskArgumentMissingException {
        super(description);
        if (deadline == null || deadline.isEmpty()) {
            throw new TaskArgumentMissingException(
                    """
                            Whoopsies!! Looks like you forgot your deadline!
                            I'll say this once: next time mark your deadline with '/deadline'.""");
        }
        this.deadline = deadline;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual task.";
    }

    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | %s", super.getSaveTaskString(), deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
