public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskDesc, String deadline) throws ConverSageException {
        super(taskDesc);

        if (deadline.isEmpty()) {
            throw new ConverSageException("Missing deadline...");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
