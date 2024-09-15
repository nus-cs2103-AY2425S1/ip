package yapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task, which is a task with a LocalDateTime fromDateTime in which the event starts, and another
 * LocalDateTime toDateTime in which the event ends.
 */
public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Creates an instance of Event.
     *
     * @param taskName     Name of the task.
     * @param fromDateTime Date and Time of the starting time of event.
     * @param toDateTime   Date and Time of the ending time of event.
     */
    public Event(String taskName, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(taskName);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        super.setTaskTag("event");
    }

    public void setFromDateTime(LocalDateTime localDateTime) {
        this.fromDateTime = localDateTime;
    }

    public void setToDateTime(LocalDateTime localDateTime) {
        this.toDateTime = localDateTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    public String toString() {
        String fromDateTimeToString = this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toDateTimeToString = this.toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String doneSymbol = super.getIsDone() ? "[X]" : "[ ]";
        return String.format("[E]%s %s (FROM: %s TO: %s)", doneSymbol, super.getName(),
                fromDateTimeToString, toDateTimeToString);
    }

    /**
     * Returns the string representation of the Event object to be recorded into a file.
     * String to be decoded when loading history from the file.
     *
     * @return String representation of the Event object.
     */
    public String toFile() {
        String fileDoneSymbol = super.getIsDone() ? "D" : "N";
        return String.format("E %s--%s--%s--%s", fileDoneSymbol, super.getName(), this.fromDateTime, this.toDateTime);
    }
}
