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

    private String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}