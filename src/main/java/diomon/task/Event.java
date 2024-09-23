package diomon.task;

import diomon.parser.Parser;

import java.time.LocalDate;

/**
 * The {@code Event} class represents a task that occurs over a specific date range.
 * It extends the {@link Task} class and includes a start date ("from") and an end date ("to").
 */
public class Event extends Task{
    public static final String TYPEICON = "E";
    LocalDate from;
    LocalDate to;
    /**
     * Constructs a new {@code Event} task with the given description, start date, and end date.
     *
     * @param task The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new {@code Event} task with the given completion status, description,
     * start date, and end date.
     *
     * @param complete {@code true} if the event is completed, {@code false} otherwise.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(boolean complete, String description, LocalDate from, LocalDate to) {
        super(complete, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the icon representing the task type ("E" for Event).
     *
     * @return A string representing the task type icon.
     */
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    /**
     * Returns a string representation of the {@code Event} task, including
     * the description and formatted date range.
     *
     * @return A string representing the {@code Event} task.
     */
    @Override
    public String toString() {
        return super.description + String.format(" (From: %s To: %s)", this.from.format(Parser.DATEFORMATTER), this.to.format(Parser.DATEFORMATTER));
    }

    /**
     * Returns a string representation of the {@code Event} task formatted for storage,
     * which includes the type, status, description, and formatted start and end dates.
     *
     * @return A string formatted for storage.
     */
    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),this.description, this.from.format(Parser.DATEFORMATTER), this.to.format(Parser.DATEFORMATTER));
    }

    /**
     * Checks whether this {@code Event} task is equal to another object.
     * Two {@code Event} tasks are considered equal if they have the same start date,
     * end date, completion status, and description.
     *
     * @param other The object to compare to.
     * @return {@code true} if the tasks are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Event temp) {
            return temp.from.equals(from) && temp.to.equals(to) && temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}