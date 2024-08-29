package dgpt.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of type "Deadline" in the DGPT application.
 * <p>
 * A {@code Deadline} is a type of {@code Task} that has a specific due date.
 * It is characterized by its description, which is inherited from the {@code Task} class.
 * </p>
 */
public class Deadline extends Task {

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
    private LocalDate dueDate;

    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     * @param description The description of the {@code Deadline} task. This is a brief text that describes
     *                    what needs to be done.
     * @param dueDate The due date of the {@code Deadline} task, in a dd/MM/yyyy format.
     * @throws DateTimeParseException If the provided date is in an invalid format.
     */
    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDate, inputFormatter);
    }

    public String getDueDateString() {
        return this.dueDate.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDateString() + ")";
    }
}
