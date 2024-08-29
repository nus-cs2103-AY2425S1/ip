package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * This task.Deadline class extends task.Task and represents deadlines with a specified end date.
 */

public class Deadline extends Task {

    public String deadlineString;
    public LocalDateTime deadlineDateTime;
    public final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Checks if the deadline can be parsed to LocalDateTime object
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadlineString = deadline;
        try {
            this.deadlineDateTime = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.deadlineDateTime = null;
        }
    }

    @Override
    public String toString() {
        if (this.deadlineDateTime != null) {
            return taskIsDone ? "[D][X] " + this.description + " (by: " + this.deadlineDateTime.format(OUTPUT_FORMAT) + ")" : "[D][ ] " + this.description + " (by: " + this.deadlineDateTime.format(OUTPUT_FORMAT) + ")";
        }

        return taskIsDone ? "[D][X] " + this.description + " (by: " + deadlineString + ")" : "[D][ ] " + this.description + " (by: " + deadlineString + ")";

    }
}
