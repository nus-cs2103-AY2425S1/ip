package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the GPT application.
 * A Deadline task has a description and a due date/time by which it must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the task.
     * @param by The due date/time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for saving to a file.
     * The format is:
     * "D | done status | description | due date/time | priority"
     *
     * @return A string representation of the Deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s | %d",
                isDone ? 1 : 0,
                description,
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                priority);
    }

    /**
     * Returns a string representation of the Deadline task suitable for displaying to the user.
     * The format is:
     * "[D][done status] description (by: due date/time) (Priority: priority)"
     *
     * @return A string representation of the Deadline task for display.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s) (Priority: %s)",
                isDone ? "X" : " ",
                description,
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")),
                getPriorityLabel());
    }

    /**
     * Returns the type of the task, which is "D" for Deadline tasks.
     *
     * @return The type of the task as a string.
     */
    @Override
    protected String getTaskType() {
        return "D";
    }
}
