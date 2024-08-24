import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        this.dateTime = date;
    }
    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.date = date;
    }


    public static Deadline of(String description, String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(by, formatter);
            return new Deadline(description, date);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(by);
                return new Deadline(description, date);
            } catch (DateTimeParseException e2) {
                return new Deadline(description, by);
            }
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
