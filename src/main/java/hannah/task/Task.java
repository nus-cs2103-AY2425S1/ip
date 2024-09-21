package hannah.task;

/**
 * The Task class represents a generic task with a name and a status indicating whether it is done.
 */
public class Task {
    private boolean isDone;
    private String name;

    /**
     * Initializes a Task with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Toggles the status of the task between done and not done.
     */
    public void setDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a string representation of the task, including its status and name.
     *
     * @return A string representing the task.
     */
    public String toString() {
        String done = this.isDone ? "X" : "  ";
        return ("[" + done + "] " + this.name);
    }
    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a formatted string to save the task in a file.
     *
     * @return An empty string, intended to be overridden by subclasses.
     */
    public String saveTaskFormat() {
        return "";
    }

    /**
     * Sets the duration of the task (to be overridden by subclasses if needed).
     *
     * @param startDuration The start duration as a string.
     * @param endDuration   The end duration as a string.
     */
    public void setDuration(String startDuration, String endDuration) {
    }

    /**
     * Sets the deadline of the task (to be overridden by subclasses if needed).
     *
     * @param deadline The deadline as a string.
     */
    public void setDeadline(String deadline) {
    }

    /**
     * Returns the status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
