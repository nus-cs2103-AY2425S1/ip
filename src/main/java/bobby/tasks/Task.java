package bobby.tasks;

/**
 * The Task class represents how a general task will look like.
 * A Task has a description and completion status.
 */
public abstract class Task {
    private boolean isCompleted;
    private final String description;

    /**
     * Constructs a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
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
        this.isCompleted = true;
    }

    /**
     * Indicates a task as incomplete
     */
    public void indIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Return the completion status of a task.
     *
     * @return "X" is task is completed "" if incomplete.
     */
    public String getStatusIcon() {
        if (this.isCompleted) {
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
