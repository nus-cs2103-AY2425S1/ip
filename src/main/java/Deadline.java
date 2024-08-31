public class Deadline extends Task {

    private String by;
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getDescription() + " | " + by;
        } else {
            return "D | 0 | " + this.getDescription() + " | " + by;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
