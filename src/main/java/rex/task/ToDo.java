package rex.task;

/**
 * The {@code ToDo} class represents a task that needs to be done without any specific deadline or time constraints.
 * It extends the {@code Task} class and adds a type identifier to distinguish it as a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The completion status of the ToDo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task, including its type identifier "[T]"
     * and the information from the {@code Task} superclass.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string suitable for saving to a file. The format includes a type identifier "T"
     * followed by the formatted output from the {@code Task} superclass.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String formatter() {
        return "T | " + super.formatter();
    }
}
