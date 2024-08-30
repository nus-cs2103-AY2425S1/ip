package yapper.app;

/**
 * Represents a "To-Do" task.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param desc the description of the to-do task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a formatted description of the event meant an input into the save file.
     * The format is: "| T | {description}".
     *
     * @return a string representing the formatted description of the to-do task
     */
    @Override
    public String getDesc() {
        return "| T | " + super.getDesc();
    }

    /**
     * Returns a string representation of the to-do task.
     * The format is: "[T]{description}".
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
