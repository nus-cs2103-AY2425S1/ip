public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getSavedDataString() {
        return "D" + " | " + super.getSavedDataString() + " | " + by;
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) {
        return new Deadline(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2], savedDataArr[3]);
    }
}
