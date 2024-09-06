package secondmind;

/**
 * Represents a ToDo task, which is a task without a specific time frame.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for the ToDoTask class.
     *
     * @param task The description of the ToDo task.
     */
    public ToDoTask(String task) {
        super(task);
    }

    /**
     * Gets the string representation of the ToDo task for storage.
     *
     * @return The storage representation of the ToDo task.
     */
    @Override
    public String getStorageRepresentation() {
        if (this.isdone) {
            return "T|1|" + this.description;
        } else {
            return "T|0|" + this.description;
        }
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[T]");
        sb.append(super.toString());
        return sb.toString();
    }
}