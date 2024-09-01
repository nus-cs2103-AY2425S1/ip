package lumina.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Lumina.Main.Lumina.Task.Task
     *
     * @param description description of the task
     */
    public Task(String description) {
        description = description.trim();
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Lumina.Main.Lumina.Task.Task
     *
     * @param description description of the task
     * @param isDone status of task
     */
    public Task(String description, boolean isDone) {
        description = description.trim();
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns string representation of status and description for save file
     *
     * @return string representation of status and description for save file
     */
    public String getStatusAndDescription() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the task suitable for saving
     *
     * @return string representation of the task in data file
     */
    public abstract String saveString();

    /**
     * Returns status icon of a given task
     *
     * @return status icon which is 'X' if done else ' '
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    /**
     * Marks the status of task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * marks the staus of task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Overrides the toString method to give appropriate string representation of
     * a task which is status icon in square brackets followed by the description
     *
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}