package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDate startEvent;
    private final LocalDate endEvent;

    /**
     * Constructs an event task with the specified task name that is not completed by default.
     * The start and end dates of the event are parsed from the task name.
     *
     * @param taskName The task name.
     */
    public Event(String taskName) {
        super(taskName.split("/from")[0]);
        this.startEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[0].trim());
        this.endEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[1].trim());
    }

    /**
     * Constructs an event task with the specified task name and completion status.
     *
     * @param taskName The task name.
     * @param isCompleted The completion status.
     */
    public Event(String taskName, boolean isCompleted) {
        super(taskName.split("/from")[0], isCompleted);
        this.startEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[0].trim());
        this.endEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[1].trim());
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + this.startEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        return "E " + (super.isCompleted() ? "0" : "1") + " "
                + this.getTaskName() + "/from " + this.startEvent + " /to " + this.endEvent;
    }

    /**
     * Returns the start date of the event task.
     *
     * @return The start date of the event task.
     */
    public LocalDate getStartEvent() {
        return this.startEvent;
    }

    /**
     * Returns the end date of the event task.
     *
     * @return The end date of the event task.
     */
    public LocalDate getEndEvent() {
        return this.endEvent;
    }
}
