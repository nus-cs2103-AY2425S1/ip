package bing.task;

import bing.task.Task;
import bing.task.TaskStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified information and deadline.
     *
     * @param info The description of the task.
     * @param deadline The deadline for the task.
     */
    public Deadline(String info, LocalDateTime deadline) {
        super(info);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string that describes the Deadline task in a human-readable format,
     *         including its status, description, and deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return "[D]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo() + " (by: " + deadline.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for file storage.
     *
     * @param formatter The DateTimeFormatter used to format the deadline.
     * @return A string that represents the Deadline task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "D | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + deadline.format(formatter);
    }
}
