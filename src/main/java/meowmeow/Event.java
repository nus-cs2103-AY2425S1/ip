package meowmeow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has start date and end date
 */
class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date
     * and end date.
     *
     * @param description The description of the task.
     * @param from The start date of the task in the format "yyyy-MM-dd".
     * @param to The end date of the task in the format "yyyy-MM-dd".
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    /**
     * Returns a string representation of the Event task, including the task type,
     * status, description, and start date and end date in the format "MMM dd yyyy".
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the Event task into a format suitable for saving to a file.
     *
     * @return A string representing the Event task in a file format.
     */
    @Override
    public String convertToFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}