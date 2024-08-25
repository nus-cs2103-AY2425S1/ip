public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, TaskType type) {
        super(description, type);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by, TaskType type) {
        super(description, isDone, type);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + by;
    }
}
