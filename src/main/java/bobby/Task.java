package bobby;

/**
 * The Task class represents how a general task will look like.
 * A Task has a description and completion status.
 */
public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }

    /**
     * Returns description of a task.
     *
     * @return description of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates a task as completed.
     */
    public void indComplete() {
        this.completed = true;
    }

    /**
     * Indicates a task as incomplete
     */
    public void indIncomplete() {
        this.completed = false;
    }

    /**
     * Return the completion status of a task.
     *
     * @return "X" is task is completed "" if incomplete.
     */
    public String getStatusIcon() {
        if(this.completed) {
            return "X";
        }
        return "";
    }

    /**
     * Returns the type of task.
     *
     * @return "T" for Todo, "D" for Deadline or "E" for Event
     */
    public abstract String getTaskType();

    /**
     * Returns the task representation when stored in a file.
     *
     * @return String representation of how the task is stored in a file.
     */
    public abstract String taskInFile();
}
