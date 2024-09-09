package taskalyn;

/**
 * Represents an Event Task.
 */
public class EventTask extends Task {
    private String from;
    private String to;

    /**
     * Constructs the EventTask object with description, from time, to time, and completion status.
     *
     * @param taskItem Description of Event Task.
     * @param from From timing of event.
     * @param to To timing of event.
     * @param isCompleted Whether an Event Task is completed or not.
     */
    public EventTask(String taskItem, String from, String to, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String expression of the EventTask.
     *
     * @return String expression of EventTask
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a String expression used in database file.
     *
     * @return String expression used in database file.
     */
    @Override
    public String toDatabaseFormat() {
        return "E | " + (this.isCompleted() ? "1" : "0") + " | "
                + this.getTaskDescription() + " | " + this.from + " | " + this.to;
    }
}
