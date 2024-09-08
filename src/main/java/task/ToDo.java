package task;

/**
 * Represents a ToDo type Task.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task with the specified name.
     *
     * @param name The name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "T , {0 if not complete, 1 if complete} , {name}".
     *
     * @return String description of task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "T , " + (isComplete() ? 1 : 0) + " , " + getName() + "\n";
    }

    /**
     * Returns the String representation of the ToDo task as shown to the user on the HypeBot UI.
     * Should be in this form: "[T][{X only if complete}] {name}".
     *
     * @return String representation of task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
