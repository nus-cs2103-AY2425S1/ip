package spike.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return Start date and time of the event.
     */
    public LocalDateTime getFrom() {
        return LocalDateTime.from(this.from);
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return End date and time of the event.
     */
    public LocalDateTime getTo() {
        return LocalDateTime.from(this.to);
    }

    /**
     * Converts a LocalDateTime object to a string.
     * The format of the string is "d MMM yyyy HH:mm".
     *
     * @param dateTime LocalDateTime object to convert.
     * @return String representation of the LocalDateTime object.
     */
    public String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
    }

    /**
     * Updates the task based on the update type and updated part.
     *
     * @param updateType  The type of update to be made.
     * @param updatedPart The updated part of the task.
     * @return Task with the updated part.
     */
    public Task updateTask(String updateType, String updatedPart) throws IllegalArgumentException {
        switch (updateType) {
        case "description":
            return new Event(updatedPart, this.from, this.to);
        case "date time":
            if (this.from.isAfter(LocalDateTime.parse(updatedPart))) {
                throw new IllegalArgumentException("Start date and time cannot be after end date and time");
            }
            return new Event(this.getDescription(), LocalDateTime.parse(updatedPart), this.to);
        case "end date time":
            if (this.to.isBefore(LocalDateTime.parse(updatedPart))) {
                throw new IllegalArgumentException("End date and time cannot be before start date and time");
            }
            return new Event(this.getDescription(), this.from, LocalDateTime.parse(updatedPart));
        default:
            throw new IllegalArgumentException("Please enter a valid option");
        }
    }

    /**
     * Returns the task type.
     *
     * @return Task type.
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    /**
     * Converts the Event object to a string that can be saved to a file.
     * The format of the string is "E | 0 | description | start date and time | end date and time".
     *
     * @return String representation of the Event object for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.from.toString() + " | " + this.to.toString();
    }

    /**
     * Converts the Event object to a string.
     * The format of the string is "[E][status] description (from: start date and time to: end date and time)".
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateToString(this.from) + " to: " + dateToString(this.to) + ")";
    }
}
