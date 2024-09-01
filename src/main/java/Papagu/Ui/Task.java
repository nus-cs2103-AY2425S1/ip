package Papagu.Ui;

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
     * Returns if the task is done or not
     * 
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String toFile() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
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

    /**
     * Returns if the task matches the keyword.
     * @param keyword
     * @return
     */
    public boolean isMatching(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }
}
