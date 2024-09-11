package neuro.task;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Description should not be empty!";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its completion status and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns a string formatted for saving the task's data to a file.
     * The format includes the task's completion status (1 for done, 0 for not done)
     * and its description.
     *
     * @return a string formatted for saving the task's data
     */
    public String toSaveData() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }
}
