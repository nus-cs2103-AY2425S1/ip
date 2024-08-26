import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task that has a specific due date.
 * It extends the Task class by adding a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime date;

    /**
     * Constructs a new Deadline with the given description and due date.
     *
     * @param description The description of the task.
     * @param date The due date of the task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the deadline, including its status icon, description,
     * and due date.
     *
     * @return A string in the format "[D][statusIcon] description (by: due date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
