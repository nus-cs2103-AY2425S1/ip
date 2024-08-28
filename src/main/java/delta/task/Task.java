package delta.task;

/**
 * Includes a description of the task and a completion status.
 * Serves as a parent class for subclasses Todo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task instance.
     *
     * @param description Description of task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Formats Task for saving.
     *
     * @return Formatted string containing details of Task.
     */
    public String saveDetails() {
        return (isDone ? "1" : "0") + " | " + getDescription();
    }
}
