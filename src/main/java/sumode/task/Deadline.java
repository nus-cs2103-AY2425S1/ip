package sumode.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class for various tasks with deadlines
 */
public class Deadline extends Task {

    private final String due;
    private LocalDate dueDate;

    /**
     * Constructor for Deadline
     *
     * @param name Name of task.
     * @param due Due date of task.
     */
    public Deadline(String name, String due) {
        super(name);
        this.due = due;
        try {
            dueDate = LocalDate.parse(due);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + (dueDate == null ? this.due : this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    @Override
    public String savedString() {
        return "D | " + super.savedString() + " | " + this.due;
    }
}
