package reminderebot.task;

import java.time.LocalDateTime;

/**
 * Deadline represents a Task with a datetime due.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Create a Deadline from the user input.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    };

    /**
     * Creates an entry to a file from a Deadline
     * @return string representing the Deadline
     */
    @Override
    public String toFile() {
        return "D|" + getStatusIcon() + "|" + description + "|" + by;
    }

    /**
     * Create a Deadline from the text in text file.
     * @return Deadline
     */
    @Override
    public Deadline createFromFile(String file) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the String representing the Deadline to be displayed in tasklist.
     * @return string representing Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}