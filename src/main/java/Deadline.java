import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getStatusIcon() {
        return super.getStatusIcon();
    }
    @Override
    public String toFormatted() {
        return "D," + this.isDone() + "," + this.description + "," + this.by + "\n";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getDayOfMonth() + " " + by.getMonth() + " " + by.getYear() + ")";
    }
}
