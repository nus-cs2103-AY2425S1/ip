package lemon.task;
/**
 *  Parent class representing the tasks that could be added into lemon
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description description of task
     * @param type type of the task
     * @param isDone whether the task is completed or not
     */
    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is completed or not
     * @return String "X" representing the task is complete
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the type of the task
     * @return String for the type of the task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description of the task
     * @return String for the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task as uncompleted
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Abstract class that returns the string format for storing into the file
     * Use "|" as dividers between different params, ending the format with "\n"
     * @return String that have the proper format to be stored into the file
     */
    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
