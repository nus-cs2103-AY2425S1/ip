package tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a task with a deadline that needs to be completed before a specific date and time.
 * This class extends the Task class and implements Serializable for object serialization.
 */
public class Deadline extends Task implements Serializable {
    private LocalDateTime deadline;

    /**
     * Creates task that need to be done before a specific date/time.
     *
     * @param task task information of the deadline task.
     * @param deadline the time by which the task needs to be completed,
     *                 in the format "yyyy/MM/dd HH:mm".
     * @throws DateTimeParseException if the deadline string is not in the correct format.
     */
    public Deadline(String task, String deadline) throws DateTimeParseException {
        this.task = task;
        this.deadline = LocalDateTime.from(LocalDateTime.parse(deadline,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm", Locale.US)));
    }

    /**
     * Returns the information of a deadline task.
     *
     * @return information the task in "[D][-] Task (by: 'deadline')" format.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
                "MMM dd yyyy HH:mm", Locale.US);
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(outputFormat) + ")";
    }
}
