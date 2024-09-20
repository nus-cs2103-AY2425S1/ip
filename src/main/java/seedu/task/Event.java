package seedu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that consists of a description, start date, and end date.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an {@code Event} task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param start The start date of the event task in the format YYYY-MM-DD.
     * @param end The end date of the event task in the format YYYY-MM-DD.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Converts the {@code Event} object into a string representation for display purposes.
     * The format includes the task type, status, description, start date, and end date.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        String starting = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String ending = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + starting + " to: " + ending + ")";
    }

    /**
     * Converts the {@code Event} object into a string format suitable for saving in a text file.
     * The format includes task type, completion status, description, start date, and end date.
     *
     * @return A formatted string representing the event task to be saved.
     */
    @Override
    public String toSave() {
        return "E" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    /**
     * Compares this {@code Event} with the specified object for equality.
     * Two {@code Event} objects are considered equal if they have the same description,
     * start date, and end date.
     *
     * @param obj The object to compare this {@code Event} with.
     * @return {@code true} if the specified object is an {@code Event} with the same description, start date,
     *      and end date;
     *      {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Event) {
            Event t = (Event) obj;
            if (this.description == null || t.description == null) {
                return false;
            }
            if (this.start == null || t.start == null) {
                return false;
            }
            if (this.end == null || t.end == null) {
                return false;
            }

            return this.description.equals(t.description)
                    && this.end.equals(t.end)
                    && this.start.equals(t.start);
        }

        return false;
    }
}
