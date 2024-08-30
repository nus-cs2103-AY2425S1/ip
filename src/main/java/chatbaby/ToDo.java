package chatbaby;

import chatbaby.Task;

/**
 * Represents a to-do task.
 * A <code>ToDo</code> object corresponds to a task with a description
 * that needs to be done without any specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given name.
     *
     * @param name The description of the to-do task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts this ToDo task to a string suitable for saving to a file.
     *
     * @return The string representation of this task for file storage.
     */
    @Override
    public String toFileText() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns a string representation of this ToDo task.
     *
     * @return The string representation of this ToDo task, including its type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
