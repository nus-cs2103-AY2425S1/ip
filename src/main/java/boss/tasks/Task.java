package boss.tasks;

/**
 * Represents the Task class.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object
     * @param description description of task
     * @param isDone status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status icon of task
     * @return returns status icon of task
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks Task as done.
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as undone.
     */

    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Gets description of Task
     * @return description of Task
     */
    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return "none";
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
