package models;

public class Deadline extends Task {

    private String by;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    private String getBy() {
        return this.by;
    }
    public String serialize() {
        return String.format("D|%s|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription(),
                this.getBy());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}