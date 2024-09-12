package cheese.task;

import cheese.exception.CheeseException;

/**
 * Basically same as Task
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo from a name
     * @param name for task
     * @throws CheeseException if name is blank
     */
    public ToDo(String name) throws CheeseException {
        super(name);
    }

    /**
     * Create a ToDo from csv data
     * @param data data from Storage file
     * @throws CheeseException if data is in wrong format
     */
    public ToDo(String[] data) throws CheeseException {
        super(data);
    }

    /**
     * Returns correct format for user to create a ToDo
     * @return String of the correct command
     */
    public static String correctFormat() {
        return "todo [name]";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
