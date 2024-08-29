package james;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the specified description and mark status.
     * @param desc The description of the task.
     * @param mark The mark status of the task.
     * @throws MissingDescriptionException If the description is empty.
     */
    public Todo(String desc, Boolean mark) throws MissingDescriptionException {
        super(desc, mark);
    }

    /**
     * Returns a string representation of the to-do task for display.
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    /**
     * Returns a string representation of the to-do task for saving to a file.
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %s", super.toFileFormat());
    }
}
