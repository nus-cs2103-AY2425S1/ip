package duke.tasks;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructor for a new to-do task.
     *
     * @param name the name of the to-do task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Get the string representation of the to-do task.
     * @return the string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
