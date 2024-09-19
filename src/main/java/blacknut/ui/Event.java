package blacknut.ui;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time in the Blacknut application.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start date/time in the format yyyy-MM-dd HH:mm.
     * @param to The end date/time in the format yyyy-MM-dd HH:mm.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Returns the LocalDate of the event start time for comparison.
     *
     * @return The LocalDate of the event start time.
     */
    public LocalDate getDate() {
        return from.toLocalDate();
    }

    /**
     * Converts the Event task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toFileFormat() + " | " + from.format(outputFormatter) + " | " + to.format(outputFormatter);
        //return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string in the format "[E][status] description (from: MMM dd yyyy, HH:mm to: MMM dd yyyy, HH:mm)".
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

        return "[E]" + super.toString() + " (from: " + from.format(displayFormatter) + " to: " + to.format(displayFormatter) + ")";
    }
}

