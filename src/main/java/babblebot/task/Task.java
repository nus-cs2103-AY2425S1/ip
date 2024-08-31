package babblebot.task;

/**
 * The Task class represents a general task with a description and a completion status.
 * It serves as the base class for more specific types of tasks such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, " " if the task is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts the Task to a format suitable for file storage.
     *
     * @return A string representation of the Task in file format.
     */
    public String toFileFormat() {
        if (getStatusIcon().equals("X")) {
            return "1" + " | " + this.description;
        } else {
            return "0" + " | " + this.description;
        }
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string representation of the Task, including its status and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

