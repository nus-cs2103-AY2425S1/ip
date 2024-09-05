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
     * Returns a string representation of the ToDo task, including its type indicator "[T]"
     * and its completion status.
     *
     * @return A string representing the ToDo task in the format "[T][X] description" if done,
     * or "[T][ ] description" if not done.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo task to a string that can be written to a file.
     *
     * @return A formatted string in the format "T | {isDone} | {description}" where
     * {isDone} is "1" if the task is done and "0" if it is not.
     */
    @Override
    public String parseToFile() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}

