package orion.tasks;

import orion.exceptions.OrionInputException;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    private String body;
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     */
    public Task(String body) {
        this.body = body;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with the specified description and completion status.
     *
     * @param body the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String body, boolean isDone) {
        this.body = body;
        this.isDone = isDone;
    }


    /**
     * Returns the description of the task
     *
     * @return the body of the {@code Task}
     */
    public String getBody() {
        return body;
    }

    /**
     * Marks the task as done.
     *
     * @throws OrionInputException if the task is already marked as done
     */
    public void setDone() throws OrionInputException {
        if (this.isDone) {
            throw new OrionInputException("Task has already been set as completed!");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     *
     * @throws OrionInputException if the task is already marked as undone
     */
    public void setUndone() throws OrionInputException {
        if (!this.isDone) {
            throw new OrionInputException("Task has already been set as uncompleted!");
        }
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The format is "[X] description" if the task is done, or "[ ] description" if the task is not done.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.body
                : "[ ] " + this.body;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * The format is "T,description" if the task is done, or "F,description" if the task is not done.
     *
     * @return the string representation of the task for saving
     */
    public String saveString() {
        return this.isDone
                ? "T," + this.body
                : "F," + this.body;
    }
}
