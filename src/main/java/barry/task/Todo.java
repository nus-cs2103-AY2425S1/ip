package barry.task;

/**
 * Represents a to-do task given by the user.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task in a format to save the file.
     * The format consists of the type of task (ie T for To-do), and the task's completion status,
     * followed by the task description.
     *
     * @return A String representing the to-do task in save format.
     */
    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }
    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
