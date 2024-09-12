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
     * Constructor for Deadline class with empty notes
     * @param description Description of deadline task
     * @param deadline Date of deadline for task
     * @throws DateTimeParseException Thrown if deadline string not given in the form yyyy-MM-dd
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        this(description, "", deadline);
    }

    /**
     * General constructor for Deadline class
     * @param description task description
     * @param notes task notes
     * @param deadline deadline date
     * @throws DateTimeParseException thrown if deadline string is not given in the form yyyy-MM-dd
     */
    public Deadline(String description, String notes, String deadline) throws DateTimeParseException {
        super(description, notes);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String getTaskForSaving() {
        return String.format(
                "D | %d | %s | %s | %s\n", (
                this.isDone ? 1 : 0),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.notes);
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)%s",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.notes.isEmpty() ? "" : String.format(" (%s)", this.notes)
        );
    }
}
