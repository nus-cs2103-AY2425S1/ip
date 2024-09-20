package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a duke.task.Deadline task with description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks if this deadline is upcoming within a given number of days.
     *
     * @param today the current date.
     * @param daysAhead the number of days ahead to check for.
     * @return true if the deadline is upcoming within the specified number of days.
     */
    public boolean isUpcoming(LocalDate today, int daysAhead) {
        long daysUntilDue = LocalDate.now().until(by).getDays();
        return daysUntilDue >= 0 && daysUntilDue <= daysAhead;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return A string representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of this deadline task to be saved in duck.txt.
     *
     * @return A string representation of this deadline task.
     */
    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}
