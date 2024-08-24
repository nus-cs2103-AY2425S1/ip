package duke;

/**
 * Represents an event task in the task management system.
 * An event task has a description, a start time, and an end time.
 */
public class Event extends IndividualTask {

    private String from;
    private String to;

    /**
     * Constructs an {@code Event} object with the specified task description, start time, and end time.
     *
     * @param taskDescription The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task in a format suitable for saving to a file.
     *
     * @return The string representation of the task in the format
     * "E | statusIcon | taskDescription | from | to".
     */
    @Override
    public String saveToFileFormat() {
        return "E | " +
                this.getSaveIcon() +
                " | " +
                this.getTaskDescription() +
                " | " + this.from +
                " | " + this.to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the task, including the
     * type, status, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" +
                super.toString() +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}
