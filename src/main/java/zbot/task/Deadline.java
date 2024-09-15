package zbot.task;

import java.time.LocalDateTime;

import zbot.Parser;

/**
 * Represents a deadline with a description and due datetime.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline.
     * @param dueDate     Due date of the deadline.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for Deadline with note.
     *
     * @param description Description of the deadline.
     * @param dueDate     Due date of the deadline.
     * @param note        Note of the deadline.
     */
    public Deadline(String description, LocalDateTime dueDate, Note note) {
        super(description, note);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for Deadline with note content.
     *
     * @param description Description of the deadline.
     * @param dueDate     Due date of the deadline.
     * @param note        Note content of the deadline.
     */
    public Deadline(String description, LocalDateTime dueDate, String note) {
        super(description, note);
        this.dueDate = dueDate;
    }

    /**
     * Returns the due datetime of the deadline.
     *
     * @return Due datetime of the deadline.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatDateTimeToOutput(dueDate));
    }

}
