import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private Parser parser;
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        parser = new Parser();
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        parser = new Parser();
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toSave() {
        return "D" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + parser.dateToString(by);
    }
}
