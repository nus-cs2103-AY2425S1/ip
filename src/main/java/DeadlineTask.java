public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String description, String deadline) throws TaskArgumentMissingException {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual task.";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
