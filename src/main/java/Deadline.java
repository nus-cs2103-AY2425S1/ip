public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
