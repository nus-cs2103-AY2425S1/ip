import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private final LocalDateTime due;

    /**
     * Constructor for Deadline.
     * @param description a String describing the Deadline
     * @param due a String describing the due date/time
     */
    public Deadline(String description, String due) {
        super(description.strip());
        this.due = Formatter.parseDateTimeString(due.strip().substring(3));
    }

    public LocalDateTime getDue() {
        return due;
    }

    /**
     * Returns a formatted String that represents the Deadline object and its fields
     * @return a String representation of the Deadline object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), Formatter.printDateTime(due));
    }
}
