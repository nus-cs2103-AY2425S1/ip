public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructor to create a DeadlineTask object
     *
     * @param description that describes what the task is
     * @param deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
