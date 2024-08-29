package snipe.task;

/**
 * The {@code ToDo} class represents a task that needs to be done,
 * without any specific deadline or time frame. It is a subclass of {@link Task}.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     * The task type is set to {@link TaskType#TODO}.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the task formatted for storage in a file.
     *
     * @return A string representation of the task in the format "T | status | description".
     */
    @Override
    public String parseToFileFormat() {
        String status = getStatus() ? "1" : "0";
        return "T | " + status + " | " + this.description;
    }

    /**
     * Returns a string representation of the {@code ToDo} task,
     * including its type indicator and description.
     *
     * @return A string in the format: "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
