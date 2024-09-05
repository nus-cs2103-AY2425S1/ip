package dumpling.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class, inherits Task
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for Deadline class
     * @param description Description of deadline task
     * @param deadline Date of deadline for task
     * @throws DateTimeParseException Thrown if deadline string not given in the form yyyy-MM-dd
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String getTaskForSaving() {
        return String.format(
                "D | %d | %s | %s\n", (
                this.isDone ? 1 : 0),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
