package BrainRot;

/**
 * The ToDo class represents a basic task without a specific deadline.
 * It extends the Task class and provides a simple implementation for tasks that
 * are meant to be done without any time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param task The description of the task to be done.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Converts the ToDo task into a formatted string suitable for saving to a file.
     *
     * @return A string representing the ToDo task in the format "[T][X or  ]/description".
     */
    @Override
    public String toFileString() {
        return "[T][" + (isDone ? "X" : " ") + "]/" + description;
    }

    /**
     * Converts the ToDo task into a formatted string for display.
     *
     * @return A string representing the ToDo task in the format "[T][X or  ] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
