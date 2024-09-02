public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
            super(description);
            this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | " + this.by;
    }
}
