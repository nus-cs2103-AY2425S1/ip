package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 */
public class Event extends Task {
    private static final String displayDateFormat = "MMM d yyyy";
    private static final String storageDateFormat = "yyyy-MM-dd";

    private LocalDate fromDate;

    private LocalDate toDate;

    /**
     * Constructs a deadline task with the specified description, start date and due date.
     * By default, the task is uncompleted.
     *
     * @param description
     * @param fromDate
     * @param toDate
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Formats the task into a string for storage, including the task type ("E" for event),
     * its completion status, description, start date and due date.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        String formattedFromDate = this.fromDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        String formattedToDate = this.toDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        return String.format("E | %s | %s | %s", super.formatString(), formattedFromDate, formattedToDate);
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked), its description, start date and due date.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String formattedFromDate = this.fromDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        String formattedToDate = this.toDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        return String.format("[E] %s (from: %s to: %s)", super.toString(), formattedFromDate, formattedToDate);
    }
}
