package noisy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that extends the Task class.
 * An Event task includes a start date and an end date for the event.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an Event task with the specified description, status, start date, and end date.
     *
     * @param description The description of the event.
     * @param isDone Whether the task is marked as done.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String description, boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Formats the Event task in a specific format for saving into a file.
     * Format: E | description | status | start date | end date
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String formatTask() {
        String status = isDone? "1" : "0";
        return "E | " + this.description + " | " + status + " | " + this.startDate + " | " + this.endDate;
    }

    /**
     * Returns the string representation of the Event task, including its status, start date, and end date.
     * The start and end dates are formatted in "MMM d yyyy" format (e.g., Jan 1 2024).
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}