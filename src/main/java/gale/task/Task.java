package gale.task;

/**
 * Represents a task in a task list.
 * A task has a description and a status that indicates whether it has been done.
 * Task is an abstract class and subclasses include ToDo, Deadline, and Event.
 *
 * @author kaikquah
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private Priority priority;

    /**
     * Constructs a task with the given description and priority.
     * Task is abstract and cannot be instantiated directly.
     * @param description the description of the task
     * @param priority the priority of the task
     */
    public Task(String description, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Returns the description of the task as a String.
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task, where true indicates the task is done.
     * @return the status of the task
     */
    public boolean status() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task, where X indicates the task is done.
     * @return
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark task done with X
    }

    /**
     * Gets the priority of the task.
     * @return the priority of the task
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority of the task to a new one
     * @param priority the new priority of the task to be set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns the String representation of the task.
     * @return the String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the priority of the task as a String.
     * @return the priority of the task as a String
     */
    public String getPriorityString() {
        if (priority != Priority.NONE) {
            return " [" + priority + "]";
        }
        return "";
    }

    /**
     * Returns the String representation of the task to be saved in a file.
     * @return the String representation of the task to be saved in a file
     */
    public abstract String toFileString();
}
