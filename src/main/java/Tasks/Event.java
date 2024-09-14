package Tasks;

import enums.TaskType;

/**
 * Represents an event task with specified start and end times.
 * This class extends the {@link Task} class, adding start and end times to the basic task structure,
 * suitable for scheduling events.
 */
public class Event extends Task {
    protected String start;  // Start time of the event.
    protected String end;    // End time of the event.

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The textual description of the event.
     * @param start The start time of the event in a specific format (e.g., 'HH:mm' or 'yyyy-MM-dd HH:mm').
     * @param end The end time of the event in a specific format (e.g., 'HH:mm' or 'yyyy-MM-dd HH:mm').
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of this event task in a format suitable for file storage.
     * This includes the type indicator 'E', followed by the base task's file format, and the event's start and end times.
     *
     * @return The formatted string suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + start + " | " + end;
    }

    /**
     * Returns the string representation of the event task, including its status, description,
     * start time, and end time formatted in the specific time format.
     *
     * @return The detailed string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
