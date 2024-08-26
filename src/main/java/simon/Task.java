package simon;
/**
 * Represents a task with a name, completion status, and a number.
 * This class provides methods to manipulate and retrieve task details.
 */
public class Task {
    public String name;
    //public Integer number;
    public Boolean completed;
    public Integer number;
    public Task (String name, int number) {
        this.name = name;
        this.completed = false;
        this.number = number;
    }
    /**
     * Returns the unique identifier of the task.
     *
     * @return the task number
     */
    public int getNumber(){
        return this.number;
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
        this.completed = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.completed = false;
    }
    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * The format includes the task type, completion status, and name.
     *
     * @return a string in the format "T | completed status | task name"
     */
    public String toSaveFormat() {
        return "T | " + (completed ? 1 : 0) + " | " + name;
    }
    /**
     * Returns a string representation of the task for display to the user.
     * Includes a marker indicating whether the task is completed.
     *
     * @return a string representation of the task, including completion status
     */
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        }
        else {
            return "[ ] " + this.name;
        }
    }
}
