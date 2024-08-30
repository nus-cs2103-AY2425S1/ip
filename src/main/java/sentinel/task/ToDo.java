package sentinel.task;

/**
 * Represents a ToDo task in the Sentinel application.
 * A ToDo task is a specific type of task that only requires a description and does not have a deadline or time range.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }
}
