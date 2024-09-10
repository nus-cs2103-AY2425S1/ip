package LunaBot.task;

/**
 * Represents a ToDo task in the task list.
 * A ToDo task has a description and a completion status (whether it is done or not).
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone Whether the ToDo task is completed or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a ToDo task with the specified description and sets the completion status to not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Converts the ToDo task into a format suitable for saving to a file.
     *
     * @return A string representation of the ToDo task in the format to be stored in the file.
     *         The format is "T | 1 or 0 (done or not done) | description".
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the ToDo task, including its description and completion status.
     *
     * @return A string representation of the ToDo task. The format is "[T][X or ] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
