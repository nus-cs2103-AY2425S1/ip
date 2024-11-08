package cow.tasks;

/**
 * Represents a Todo object.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo object.
     *
     * @param isDone      The status of the Todo task.
     * @param description The description of the Todo task.
     */
    public Todo(String isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the string representation of the Todo Task.
     *
     * @return The string representation of the Todo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the save data of the Todo Task.
     *
     * @return The save data of the Todo Task.
     */
    @Override
    public String getSaveData() {
        return "T | " + super.getSaveData();
    }
}
