public class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String taskDescription() {
        return "[D]" + showMark() + " " + this.name + " (by: " + deadline + ")";
    }
}
