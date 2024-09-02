package joe.task;

import java.time.LocalDate;

/**
 * The {@code Event} class represents a task that has a start date and an end date.
 * It extends the {@code Task} class and provides specific behavior for serializing
 * and displaying event tasks.
 */
public class Event extends Task {
    /** The start date of the event. */
    protected LocalDate from;

    /** The end date of the event. */
    protected LocalDate to;

    /**
     * Constructs a new {@code Event} task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Serializes the {@code Event} task into a formatted string to be stored in a file.
     * The format is "E|isDone|description|from|to".
     *
     * @return A formatted string representing the {@code Event} task.
     */
    @Override
    public String serialize() {
        return String.format("E|%b|%s|%s|%s", this.isDone, this.description, this.from, this.to);
    }

    /**
     * Returns a string representation of the {@code Event} task for displaying to the user.
     * The format is "[E][statusIcon] description (from: startDate to: endDate)".
     * If the end date is {@code LocalDate.MAX}, it is omitted from the output.
     *
     * @return A string representing the {@code Event} task.
     */
    @Override
    public String toString() {
        String toStr = this.to.equals(LocalDate.MAX) ? "" : " to: " + this.to;
        return String.format("[E]%s (from: %s%s)", super.toString(), this.from, toStr);
    }
}
