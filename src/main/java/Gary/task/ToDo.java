package Gary.task;

/**
 * Represents a ToDo task. A ToDo task only has a description and a status indicating
 * whether it is completed or not.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the {@code ToDo} task.
     * The format includes the type of task and its status.
     *
     * @return A string representation of the {@code ToDo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the {@code ToDo} task into a string format suitable for saving to a file.
     *
     * @return A string representation of the {@code ToDo} task for file storage.
     */
    @Override
    public String parseToFile() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
