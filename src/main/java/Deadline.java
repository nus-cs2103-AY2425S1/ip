public class Deadline extends Task {
    private String deadline;

    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toStringDetails() {
        return String.format("[D]%s (by: %s)", super.toStringDetails(), this.deadline);
    }
}
