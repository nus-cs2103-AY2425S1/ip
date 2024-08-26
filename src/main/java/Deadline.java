import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private final LocalDateTime due;
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");

    /**
     * Constructor for Deadline.
     * @param description a String describing the Deadline
     * @param due a String describing the due date/time
     */
    public Deadline(String description, String due) {
        super(description.strip());
        this.due = LocalDateTime.parse(due.strip().substring(3), parseFormatter);
    }

    public String getDue() {
        return due.format(parseFormatter);
    }

    /**
     * Returns a formatted String that represents the Deadline object and its fields
     * @return a String representation of the Deadline object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), due.format(printFormatter));
    }
}
