package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline type Task with a LocalDateTime type due date.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates a Deadline task with the specified name and deadline.
     *
     * @param name The name of the deadline.
     * @param dueDateString The due date of the deadline in String form.
     */
    public Deadline(String name, String dueDateString) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDateTime.parse(dueDateString, formatter);
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "D , {0 if not complete, 1 if complete} , {name} , {dueDate}".
     *
     * @return String description of task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "D , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + dueDate.format(formatter) + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[D][{X only if complete}] {name} (by: {dueDate})".
     *
     * @return String representation of task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
