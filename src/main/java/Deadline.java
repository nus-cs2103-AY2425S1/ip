public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() + " (by: " + this.deadline + ")";
    }
}
