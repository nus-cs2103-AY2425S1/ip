package bruno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs during a specific time period.
 * It extends the Task class and includes start and end times for the event.
 */
public class Event extends Task {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the given description, start time, end time,
     * and completion status.
     *
     * @param description The description of the event task.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     * @param done The completion status of the task (true if done, false otherwise).
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean done) {
        super(description, done);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter);
    }
}
