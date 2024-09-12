package revir.tasks;
import java.io.Serializable;

/**
 * Represents a task that can be completed.
 *
 * This class provides methods to set the completion status of the task and retrieve its description.
 */
public class Task implements Serializable {
    private boolean completed = false;
    private String description;

    /**
     * Creates a new Task with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status the completion status to be set
     */
    public void setCompleted(boolean status) {
        this.completed = status;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, indicating whether it is completed or not.
     */
    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task) {
            return this.description.equals(task.description);
        }
        return false;
    }
}
