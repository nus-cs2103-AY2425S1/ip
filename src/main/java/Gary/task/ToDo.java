package Gary.task;

/**
 * The {@code ToDo} class represents a task with only a description and no specific date or time.
 * It is a subclass of the {@code Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} object with the specified description.
     *
     * @param description The description of the to-do task.
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
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}

