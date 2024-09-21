package hannah.task;

/**
 * The ToDos class represents a task that needs to be done, without a specific deadline or event duration.
 */
public class ToDos extends Task {
    /**
     * Initializes a ToDos task with the specified name.
     *
     * @param name The name of the ToDos task.
     */
    public ToDos(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDos task, including its name and status.
     *
     * @return A string representing the ToDos task.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Returns a formatted string to save the task in a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String saveTaskFormat() {
        String line = "T | " + (isDone() ? "1" : "0") + " | " + getName();
        return line + System.lineSeparator();
    }
}
