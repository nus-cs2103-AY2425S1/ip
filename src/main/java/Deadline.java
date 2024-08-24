import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime localDateTime;
    protected LocalDate localDate;
    protected boolean hasTime;
    // stopped here, need to handle how the LocalDate is printed, or different
    // formats of the input for localdate
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.localDateTime = by;
        this.hasTime = true;
    }
    public Deadline(String description, LocalDate by) {
        super(description);
        this.localDate = by;
        this.hasTime = false;
    }
    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: " + localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
