package genji.task;

/**
 * A class that deals with todo tasks
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Formats the todo task into strings used for display
     * @return The String to be saved
     */
    @Override
    public String toListString() {
        return "T" + super.toListString();
    }

    /**
     * Formats the todo task into strings used for display
     * @return The String to be displayed
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
