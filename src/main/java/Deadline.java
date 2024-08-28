import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class represents a task with a deadline
 */
public class Deadline extends Task{
    protected LocalDateTime by;

    /**
     * Constructs a deadline task with the given description and deadline
     *
     * @param description The description of the task
     * @param by The due date and time of the task
     * @throws SageException if the description or due date is empty or deadline format is invalid
     */
    public Deadline(String description, String by) throws SageException {
        super(description);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d HHmm");
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new SageException("Invalid date format! Please use the format 'yyyy-mm-dd HHmm'.");
        }
        if (description.isEmpty() || by.isEmpty()) {
            throw new SageException("You need to add a task and a deadline!! -_-");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
