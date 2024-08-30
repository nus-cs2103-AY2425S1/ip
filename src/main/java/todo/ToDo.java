package todo;
import task.Task;

/**
 * Represents a to-do task which extends the {@code Task} class.
 * <p>
 * A to-do task is a specific type of task that is denoted by a "[T]" prefix in its string representation.
 * </p>
 */
public class ToDo extends Task {
    /**
     * Constructs a {@code ToDo} with the specified name.
     *
     * @param name the name of the to-do task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String str = "[T]";
        str += super.toString();
        return str;
    }
}
