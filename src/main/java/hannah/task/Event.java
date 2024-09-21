package hannah.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that has a start and end date.
 * The event task inherits from the base class Task and has additional fields
 * for the start and end duration.
 */
public class Event extends Task {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private String name;
    private LocalDate startDuration;
    private LocalDate endDuration;

    /**
     * Creates an Event task with the given name.
     *
     * @param name The name of the event.
     */
    public Event(String name) {
        super(name);

    }
    /**
     * Returns the string representation of the Event task.
     * The string includes the event's name, start date, and end date, along with its completion status.
     *
     * @return A string in the format: [E] task name from start date to end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " from " + this.startDuration + " to " + this.endDuration;
    }

    /**
     * Sets the duration of the event by specifying the start and end date.
     *
     * This method parses the start and end duration from string format to LocalDate.
     * The date format expected is yyyy-MM-dd. If an invalid format is provided, it prints an error message.
     *
     * @param startDuration The start date of the event in yyyy-MM-dd format.
     * @param endDuration   The end date of the event in yyyy-MM-dd format.
     */
    @Override
    public void setDuration(String startDuration, String endDuration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            this.startDuration = LocalDate.parse(startDuration, formatter);
            this.endDuration = LocalDate.parse(endDuration, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format for event duration. Please use " + DATE_FORMAT + " .");
        }
    }

    /**
     * Returns the start duration of the event.
     *
     * @return The start date of the event as a LocalDate object.
     */
    public LocalDate getStartDuration() {
        return startDuration;
    }

    /**
     * Returns the end duration of the event.
     *
     * @return The end date of the event as a LocalDate object.
     */
    public LocalDate getEndDuration() {
        return endDuration;
    }
    /**
     * Returns the formatted string to be saved to a file for this event.
     * The string includes the task type, completion status, name, start date, and end date.
     *
     * @return A formatted string to represent the event task in the saved file.
     */
    @Override
    public String saveTaskFormat() {
        String line = "E | " + (isDone() ? "1" : "0") + " | "
                + getName() + " | from "
                + this.startDuration
                + " to " + this.endDuration;
        return line + System.lineSeparator();

    }
}
