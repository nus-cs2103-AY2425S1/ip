import java.time.LocalDate;

public class Deadline extends Task {
    private String by;
    private LocalDate deadline;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = DateParser.convertStringToDate(this.by);
    }
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
        deadline = DateParser.convertStringToDate(this.by);
    }
    public String getBy() {
        return by;
    }
    @Override
    public String markDone() {
        super.markDone();
        return this.toString();
    }
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return this.toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + (deadline != null ? DateParser.changePattern(deadline) : this.by) + ")";
    }
}
