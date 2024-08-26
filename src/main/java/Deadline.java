public class Deadline extends Task {
    private String deadline;

    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline (String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toStringDetails() {
        return String.format("[D]%s (by: %s)", super.toStringDetails(), this.deadline);
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s", "D", getStatusBinary(), getDescription(), this.deadline);
    }
}
