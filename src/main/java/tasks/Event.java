package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class for event-type tasks.
 */

public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates an event-type task.
     *
     * @param name Name for event task.
     * @param startTime Start Date for event task.
     * @param endTime End Date for event task.
     */
    public Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an event-type task, marking it as done or undone.
     *
     * @param name Name for event task.
     * @param startTime Start Date for event task.
     * @param endTime End Date for event task.
     * @param done True if the task is done.
     */
    public Event(String name, LocalDate startTime, LocalDate endTime, boolean done) {
        super(name, done);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    @Override
    public String toData() {
        return "E" + super.toData() + "%" + this.startTime + "|" + this.endTime;
    }
}
