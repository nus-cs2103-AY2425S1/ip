package babblebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that spans over a period of time.
 * It extends the Task class and adds LocalDate fields to store the start and end dates of the event.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event in the format "yyyy-MM-dd".
     * @param to          The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    /**
     * Converts the Event task to a format suitable for file storage.
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (getStatusIcon().equals("X")) {
            return "E" + " | " + "1" + " | " + this.description + " | " +
                    this.from.format(formatter) + " to " + this.to.format(formatter);
        } else {
            return "E" + " | " + "0" + " | " + this.description + " | " +
                    this.from.format(formatter) + " to " + this.to.format(formatter);
        }
    }

    /**
     * Returns a string representation of the Event task, including its start and end dates.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" +  super.toString() + " (from: " + this.from.format(formatter) +
                " to: " + this.to.format(formatter) + ")";
    }
}
