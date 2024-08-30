package talkie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Talkie application.
 * <p>
 * An {@code Event} task has a description and a time range indicating when the event starts and ends.
 * </p>
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} with the specified description, start date/time, and end date/time.
     *
     * @param desc The description of the event task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Serializes the event task to a string format for storage or retrieval.
     * <p>
     * The string format is as follows: "E | status | description | start date/time | end date/time".
     * </p>
     *
     * @return A string representation of the event task for storage.
     */
    @Override
    public String stringifyTask() {
        return String.format("E | %d | %s | %s | %s", super.getStatus() ? 1 : 0,
                super.getDesc(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * The string includes the task's status icon, description, start date/time, and end date/time in the format "MMM dd yyyy HH:mm".
     * </p>
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }
}
