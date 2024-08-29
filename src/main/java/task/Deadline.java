package task;

public class Deadline extends Task {
    private final String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public Deadline(String description, boolean isDone, String dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public Deadline setAsDone() {
        return new Deadline(this.getDescription(), true, this.getDueBy());
    }

    @Override
    public Deadline setAsUndone() {
        return new Deadline(this.getDescription(), false, this.getDueBy());
    }

    @Override
    public Deadline setDescription(String description) {
        return new Deadline(this.getDescription(), this.isDone(), this.getDueBy());
    }

    public String getDueBy() {
        return this.dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }
}
