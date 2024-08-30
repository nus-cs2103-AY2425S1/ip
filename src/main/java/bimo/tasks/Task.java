package bimo.tasks;

/**
 * Creates a Task with completion status and description.
 */
public class Task {
    private boolean status = false;
    private String details;

    /**
     * Instantiates a Task object.
     *
     * @param details Description of object.
     */
    public Task(String details) {
        this.details = details;
    }

    /**
     * Sets task as completed.
     */
    public void markCompleted() {
        this.status = true;
    }

    /**
     * Sets task as uncompleted.
     */
    public void markUncompleted() {
        this.status = false;
    }

    /**
     * Converts task to string value with description.
     *
     * @return String value of task.
     */
    @Override
    public String toString() {
        String status = this.status ? "X": " ";
        return String.format("[%s] %s", status, this.details);
    }

    /**
     * Formats a task to text.
     *
     * @return Text format of status and description separated by |.
     */
    public String getTaskText() {
        String state = this.status ? "1" : "0";
        return state + "|" + this.details;
    }
}
