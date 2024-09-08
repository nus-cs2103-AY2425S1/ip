package drbrown.task;

/**
 * Represents a Todo task in the DrBrown application.
 * A Todo task has a description and a status indicating whether it is completed.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified status and description.
     *
     * @param isCompleted  The completion status of the task (true if completed, false otherwise).
     * @param description  The description of the task.
     */
    public Todo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    /**
     * Returns the string representation of the Todo task in the format suitable for file storage.
     *
     * @return A string formatted for file storage representing the Todo task.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * Returns the string representation of the Todo task in the format suitable for UI display.
     *
     * @return A user-friendly string that adds a humorous comment about adding the task to the list.
     */
    @Override
    public String toUiString() {
        return "Doc, you don't just walk into a store and buy plutonium! "
                + "But you sure can add this task to your list!\n";
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string representation of the Todo task with its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
