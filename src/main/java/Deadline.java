public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toSaveString() {
        return "D" + Barney.SAVE_FILE_DELIMITER + super.toSaveString() + Barney.SAVE_FILE_DELIMITER + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
