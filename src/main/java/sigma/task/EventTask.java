package sigma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event task with the given description, start time and end time.
     *
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event task.
     *
     * @return Start time of the event task.
     */
    public LocalDateTime getStartDate() {
        return this.from;
    }
    /**
     * Returns the end time of the event task.
     *
     * @return End time of the event task.
     */
    public LocalDateTime getEndDate() {
        return this.to;
    }
    /**
     * Returns the start time of the event task in MMM d yyyy, HH:mm format.
     *
     * @return Start time of the event task.
     */
    public String getStartDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return from.format(formatter);
    }

    /**
     * Returns the end time of the event task.
     *
     * @return End time of the event task.
     */
    public String getEndDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return to.format(formatter);
    }

    /**
     * Returns the type of the task.
     *
     * @return String representation of type of the task.
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusString(), getDescription(), getStartDateString(), getEndDateString());
    }

    /**
     * Returns the date of the event task.
     *
     * @return Date of the event task.
     */
    @Override
    public String getDateString() {
        return getStartDateString() + " - " + getEndDateString();
    }

}
