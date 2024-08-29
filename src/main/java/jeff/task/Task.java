package jeff.task;

/**
 * Represents a task with a description and completion status.
 *
 * A Task object is the base class for specific types of tasks such as ToDo, Deadline, and Event.
 */
public class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param task Description of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task ("X" if done, otherwise a space).
     *
     * @return The status icon.
     */
    private String getMark() {
        return (this.isDone) ? "X" : " ";
    }

    public String getDescription() { return this.task; }

    public String toString() {
        return "[" + this.getMark() + "] " + this.task;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String saveAsCSV() {
        return this.getMark() + "," + this.task;
    }
}