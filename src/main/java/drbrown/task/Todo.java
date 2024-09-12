package drbrown.task;

/**
 * Represents a Todo task in the DrBrown application.
 * A Todo task has a description, a completion status (completed or not), and a priority level.
 * It provides methods to format the task details for file storage and UI display.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified completion status, description, and priority level.
     *
     * @param isCompleted  A boolean indicating whether the task is completed (true if completed, false otherwise).
     * @param description  A string that describes the task.
     * @param priority     A {@link Priority} enum value representing the priority level of the task.
     */
    public Todo(boolean isCompleted, String description, Priority priority) {
        super(isCompleted, description, priority);
    }

    /**
     * Returns the string representation of the Todo task in a format suitable for file storage.
     * The format includes the type of task (Todo), its completion status, description, and priority level.
     *
     * @return A string formatted for file storage representing the Todo task.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * Returns the string representation of the Todo task in a format suitable for UI display.
     * This method provides a user-friendly message that humorously describes adding the task to the list.
     *
     * @return A user-friendly string that adds a humorous comment about adding the task to the list.
     */
    @Override
    public String toUiString() {
        return "Doc, you don't just walk into a store and buy plutonium! "
                + "But you sure can add this task to your list!\n";
    }

    /**
     * Returns the string representation of the Todo task for console or text display.
     * This format includes the type of task (Todo), its completion status, and description.
     *
     * @return A string representation of the Todo task, including its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
