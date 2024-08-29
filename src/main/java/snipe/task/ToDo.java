package snipe.task;

/**
 * The {@code snipe.task.ToDo} class represents a task that needs to be done,
 * without any specific deadline or time frame. It is a subclass of {@link Task}.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code snipe.task.ToDo} task with the specified description.
     * The task type is set to {@link TaskType#TODO}.
     *
     * @param description The description of the snipe.task.ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String parseToFileFormat() {
        String status = getStatus() ? "1" : "0";
        return "T | " + status + " | " + this.description;
    }

    /**
     * Returns a string representation of the snipe.task.ToDo task,
     * including its type indicator and description.
     *
     * @return A string in the format: "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
