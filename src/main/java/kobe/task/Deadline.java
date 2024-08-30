package kobe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Duke chatbot application.
 * A deadline task is a task that needs to be completed by a specific date and time.
 */
public class Deadline extends Task {
    private LocalDateTime byWhen;

    /**
     * Constructs a Deadline task with the specified name and due date.
     *
     * @param name   The name or description of the deadline task.
     * @param byWhen The due date and time for the task.
     */
    public Deadline(String name, LocalDateTime byWhen) {
        super(name);
        this.byWhen = byWhen;
    }

    /**
     * Returns the string representation of the deadline task for saving to a file.
     *
     * @return A formatted string representing the deadline task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + byWhen.format(formatter);
    }

    /**
     * Returns the string representation of the deadline task, including its due date and time.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[D]" + super.toString() + " (by: " + byWhen.format(formatter) + ")";
    }
}
