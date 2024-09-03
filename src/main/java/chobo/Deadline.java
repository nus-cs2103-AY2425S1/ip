package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task that needs to be done before a specific date/time.
 * A chobo.Deadline object corresponds to a task with a deadline
 * in the Chobo chatbot.
 */
public class Deadline extends Task {
    private String unformattedDate;
    private LocalDateTime by;

    /**
     * Creates a new chobo.Deadline task.
     *
     * @param name The description of the task.
     * @param done The status of the task (true if done, false otherwise).
     * @param date The deadline of the task.
     */
    public Deadline(String name, boolean done, String date) {
        super(name, done);
        this.unformattedDate = date;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        this.by = LocalDateTime.parse(date.trim(), dateTimeFormatter);
    }

    @Override
    public String toFileString() {
        return String.format("%s|%d|%s|%s", this.getType(), this.getIsDone() ? 1 : 0, this.getName(), unformattedDate);
    }

    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string representation of the chobo.Deadline task, including its type,
     * status, name, and deadline.
     *
     * @return A string representing the chobo.Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
