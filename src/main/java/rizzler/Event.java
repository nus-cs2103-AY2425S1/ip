package rizzler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event occurring from a date
 * to another date.
 * Inherits from <code>Task</code>.
 */
class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * Constructs a new <code>Event</code>.
     *
     * @param name Name of the event.
     * @param startTime Start date of the event.
     * @param endTime End date of the event.
     */
    Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new <code>Event</code>,
     * but specifying if the event is completed.
     * This is explicitly for <code>FileStorage</code> to load
     * tasks saved in the file.
     *
     * @param name Name of the event.
     * @param startTime Start date of the event.
     * @param endTime End date of the event.
     * @param isDone If the event is completed or not.
     */
    Event(String name, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Task other) {
        if (other instanceof Event event) {
            if (this.startTime.compareTo(event.startTime) == 0) {
                if (this.endTime.compareTo(event.endTime) == 0) {
                    return super.compareTo(other);
                } else {
                    return this.endTime.compareTo(event.endTime);
                }
            } else {
                return this.startTime.compareTo(event.startTime);
            }
        } else {
            return super.compareTo(other);
        }
    }

    /**
     * Returns a String representation of the event.
     *
     * @return String that represents the event.
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] "
                + this.name
                + " (from: " + this.startTime.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endTime.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a String representation of the event for
     * <code>FileStorage</code> to save to the file.
     *
     * @return String that represents the event in the save file.
     */
    @Override
    String toSaveState() {
        return "E" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/"
                + this.startTime.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "/" + this.endTime.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "\n";
    }
}
