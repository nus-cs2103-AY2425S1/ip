package gravitas.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String eventType;

    /**
     * Constructor for Task class.
     *
     * @param description Description of the task.
     * @param eventType   Type of the task.
     */
    public Task(String description, String eventType) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public abstract String getDescription();

    /**
     * Formats the data of the task to be stored in the storage.
     *
     * @return Returns the formatted data of the task.
     */
    public abstract String formatData();

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is mark
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the event type of the task.
     *
     * @return a String that represents the event type of the task.
     */
    public String getEventType() {
        return this.eventType;
    }
    public void markTask() {
        this.isDone = true;
    }
    public void unMarkTask() {
        this.isDone = false;
    }

}