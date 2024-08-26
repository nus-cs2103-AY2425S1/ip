package simon;
/**
 * Represents a to-do task with a name and completion status.
 * Inherits from the {@link Task} class and provides specific formatting for display and saving.
 */
public class ToDo extends Task{
    /**
     * Constructs a ToDo task with the specified name and number.
     *
     * @param name the name or description of the to-do task
     * @param number a unique identifier for the to-do task
     */
    public ToDo(String name, int number) {
        super(name, number);
    }
    /**
     * Returns a string representation of the to-do task for display to the user.
     * Includes a task type marker and the completion status.
     *
     * @return a string in the format "[T][X] task name" or "[T][ ] task name" depending on completion status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     * Includes the task type, completion status, and name.
     *
     * @return a string in the format "T | completed status | task name"
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (completed ? 1 : 0) + " | " + name;
    }
}
