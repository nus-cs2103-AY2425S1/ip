/**
 * The Task class represents a task with a description and a completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * All tasks are initially not done.
     * 
     * @param description the string description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the completion status and the description of the task.
     * 
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
}
