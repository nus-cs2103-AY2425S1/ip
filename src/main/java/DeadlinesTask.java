public class DeadlinesTask extends Task {
    protected String deadline;

    public DeadlinesTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n",
                super.toString(),
                this.deadline);
    }
}
