package BrainRot;

/**
 * The Event class represents a task that occurs over a specific time period.
 * It extends the Task class and includes both a start and an end time.
 */
public class Event extends Task {
    protected String end;
    protected String start;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param command The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String command, String start, String end) {
        super(command);  // Only pass the task part to BrainRot.Task
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the Event task into a formatted string suitable for saving to a file.
     *
     * @return A string representing the Event task in the format "[E][1 or 0]/description/start/end".
     */
    @Override
    public String toFileString() {
        return "[E][" + (isDone ? "1" : "0") + "]/" + description + "/" + start + "/" + end;
    }

    /**
     * Converts the Event task into a formatted string for display.
     *
     * @return A string representing the Event task in the format "[E][1 or 0] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
