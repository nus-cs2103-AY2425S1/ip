package simon;
/**
 * Represents a task with a name, completion status, and a number.
 * This class provides methods to manipulate and retrieve task details.
 */
public class Task {
    private String name;
    //public Integer number;
    private Boolean isComplete;
    /**
     * Creates a Task with the specified name and number.
     *
     * @param name The name of the task.
     * @param number The number associated with the task.
     */
    public Task(String name, int number) {
        assert name != null : "Name cannot be empty cannot be null";
        this.name = name;
        this.isComplete = false;
    }
    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName1() {
        return this.name;
    }
    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public Boolean getIsComplete() {
        return this.isComplete;
    }


    /**
     * Returns the name or description of the task.
     *
     * @return the task name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isComplete = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isComplete = false;
    }
    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * The format includes the task type, completion status, and name.
     *
     * @return a string in the format "T | completed status | task name"
     */
    public String toSaveFormat() {
        return "T | " + (isComplete ? 1 : 0) + " | " + name;
    }
    /**
     * Returns a string representation of the task for display to the user.
     * Includes a marker indicating whether the task is completed.
     *
     * @return a string representation of the task, including completion status
     */
    public String toString() {
        if (this.isComplete) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
