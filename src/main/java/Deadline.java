public class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String taskDescription() {
        return "[D]" + showMark() + " " + this.name + " (by: " + deadline + ")";
    }
}
