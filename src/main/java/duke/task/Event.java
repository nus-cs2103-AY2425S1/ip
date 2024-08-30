package duke.task;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task, including its description, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the formatted string representation of the event task to be saved in storage.
     *
     * @return A formatted string for saving the event task to a file.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}