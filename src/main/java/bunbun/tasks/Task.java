/**
 * This class implements a Task object.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
package bunbun.tasks;
abstract public class Task {
    private String task;
    private boolean isComplete;

    /**
     * Instantiates a task. Initializes completion of task as false.
     *
     * @param task String description of task.
     */
    public Task(String task) {
        this.task = task;
        this.isComplete = false;
    }

    /**
     * Updates the task as complete.
     */
    public void complete() {
        this.isComplete = true;
    }

    /**
     * Returns task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the String description of task.
     *
     * @return String description of task, marking X for complete.
     */
    @Override
    public String toString() {
        if (this.isComplete()) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    /**
     * Checks if a task is complete.
     *
     * @return Boolean indicating if the task is complete.
     */
    public Boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Generates a string description of the task for storage in flat file.
     *
     * @return String description of task in the correct format for storage.
     */
    abstract public String genFileString();
}
