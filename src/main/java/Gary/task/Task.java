package Gary.task;

/**
 * Represents a task in the application. Each task has a description and a status indicating
 * whether the task is completed or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Toggles the status of the task between done and not done.
     */
    public void editStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Converts the task to a string that can be written to a file.
     *
     * @return A formatted string representation of the task for saving to a file.
     */
    public abstract String parseToFile();

    /**
     * Returns a string representation of the task, showing its description and whether it is done.
     *
     * @return A string in the format "[X] description" if done, or "[ ] description" if not done.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * Compares this Task with another object to check if they are equal. Two tasks are considered
     * equal if they have the same description and the same completion status.
     *
     * @param o The object to be compared with this task.
     * @return true if the object is a Task with the same description and completion status, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).description.equals(this.description) &&
                    (((Task) o).isDone == this.isDone);
        }
        return false;
    }
}
