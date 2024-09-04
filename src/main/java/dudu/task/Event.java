package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 */
public class Event extends Task {

    private LocalDate from;

    private LocalDate to;

    /**
     * Constructs a deadline task with the specified description, start date and due date.
     * By default, the task is uncompleted.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the task into a string for storage, including the task type ("E" for event),
     * its completion status, description, start date and due date.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        String from = this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String to = this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("E | %s | %s | %s", super.formatString(), from, to);
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked), its description, start date and due date.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String from = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String to = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E] %s (from: %s to: %s)", super.toString(), from, to);
    }
}
