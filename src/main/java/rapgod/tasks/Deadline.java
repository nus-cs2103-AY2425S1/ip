package rapgod.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import rapgod.utils.Parser;

/**
 * Represents a deadline with a description and a due date.
 * This subclass of {@link Task} includes information about the deadline date by which the task should be completed.
 */
public class Deadline extends Task {

    protected LocalDateTime due;
    /**
     * Constructs a Deadline with the specified description and due date.
     * The due date is parsed into a {@link LocalDateTime} object using the {@link Parser} class.
     *
     * @param description The description of the deadline task.
     * @param due The due date of the deadline, provided as a string.
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = Parser.parseToDateTime(due);
    }

    public void setDue(String due) {
        this.due = Parser.parseToDateTime(due);
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return The due date of the deadline as a {@link LocalDateTime} object.
     */
    public LocalDateTime getDueDate() {
        return this.due;
    }

    /**
     * Returns a string representation of the Deadline.
     * The string includes the task type identifier "[D]", the completion status, the description of the deadline,
     * and the due date formatted as "MMM dd yyyy hh:mma".
     * If the due date is "12:00am", it is omitted from the display.
     *
     * @return A string representation of the Deadline in the format "[D] [status] description (by: dueDate)".
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        String due = this.due.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        if (due.contains("12:00am")) {
            due = due.substring(0, due.length() - 8);
        }
        return String.format("[D] [%s] %s (by: %s)", mark, super.description, due);
    }
}
