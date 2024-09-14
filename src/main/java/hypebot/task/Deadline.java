package hypebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline type Task with a LocalDateTime type due date.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter UI_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate dueDate;

    /**
     * Creates a Deadline task with the specified name and deadline.
     *
     * @param name The name of the deadline.
     * @param dueDate The due date of the deadline in LocalDate form.
     */
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Takes in a LocalDate object representing a search date
     * and returns whether the Deadline is happening on the given date.
     *
     * @param date LocalDate object representing a date.
     * @return Whether the Deadline is happening on the given date.
     */
    @Override
    public boolean isHappeningOn(LocalDate date) {
        return dueDate.isEqual(date);
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "D , {0 if not complete, 1 if complete} , {name} , {dueDate}".
     *
     * @return String description of Deadline task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "D , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + dueDate.format(FILE_FORMATTER) + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[D][{X only if complete}] {name} (by: {dueDate})".
     *
     * @return String representation of Deadline task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(UI_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline deadline) {
            return super.equals(deadline) && this.dueDate.isEqual(deadline.dueDate);
        }
        return false;
    }
}
