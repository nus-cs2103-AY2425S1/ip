import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime byDate;
    private static final long serialVersionUID = 1L;

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate.format(defaultDateTimeFormatter) + ")";
    }
}
