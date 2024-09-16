package tasks;

/**
 * Represents tasks that do not have time conditions.
 */
public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    /**
     * Returns an empty string to represent no time conditions.
     */
    @Override
    public String getWriteTaskInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
