package task;

public class Deadline extends Task {
    private String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public String getDueBy() {
        return this.dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }
}
