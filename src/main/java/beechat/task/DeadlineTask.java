package beechat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Beechat chatbot application.
 * A deadline task is a task that needs to be completed by a specific date and time.
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The due date and time for the task.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task for saving to a file.
     *
     * @return A formatted string representation of the task in a file-friendly format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Returns the string representation of the deadline task with its deadline.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
