package yapbot.tasks;

/**
 * Child class of Task that has a start and end dates/times.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Returns an Event instance with isDone set to false by default.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event instance and allows isDone to be set to any boolean value.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     * @param isDone Set to true for task to be completed by default.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveTask() {
        return "E/" + super.saveTask() + "/" + this.from + "/" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + this.from + " To: " + this.to + ")";
    }
}
