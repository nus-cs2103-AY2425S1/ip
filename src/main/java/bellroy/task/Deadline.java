package bellroy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Deadline task
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;
    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super("D", description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hmma");
        String message = "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + dueDate.format(formatter) + ")";
        return message;
    }
}
