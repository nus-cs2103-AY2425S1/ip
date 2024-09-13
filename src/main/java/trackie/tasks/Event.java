package trackie.tasks;

/**
 * Represents a task with type "Event".
 */
public class Event extends Task {
    private String type = "E";
    private String start;
    private String end;

    /**
     * Constructor to create an event task.
     *
     * @param description the description of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task with a custom completion status.
     *
     * @param description the description of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     * @param status the completion of the status of the task.
     */
    public Event(String description, String start, String end, int status) {
        super(description, status);
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves relevant information about the task.
     *
     * @return A String containing the description, the start time and the end time of the task.
     */
    public String toString() {
        return(String.format("%s, (from: %s to: %s)", super.description, this.start, this.end));
    }

    /**
     * Retrieves the type of the task.
     * @return A String denoting the type of the task. In this case, the type is "E".
     */
    public String getTaskType() {
        return(this.type);
    }

    /**
     * Retrieves the start time of the task.
     *
     * @return A String denoting the start time of the task.
     */
    public String getStart() { return(this.start); }

    /**
     * Retrieves the end time of the task.
     *
     * @return A String denoting the end time of the task.
     */
    public String getEnd() { return(this.end); }
}
