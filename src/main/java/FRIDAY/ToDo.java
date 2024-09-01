package FRIDAY;

/**
 * Represents a to-do task with a description and completion status.
 * <p>
 * This class extends the Task class and provides additional
 * functionality specific to to-do tasks. It overrides methods to format
 * the task's representation for both storage and display purposes.
 * </p>
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description
     * and type.
     * <p>
     * The constructor initializes the to-do task with the given description
     * and sets the completion status based on the type parameter.
     * A type greater than 0 indicates the task is complete.
     * </p>
     *
     * @param description the description of the to-do task
     * @param type the type of the task; if greater than 0, the task is complete
     */
    public ToDo(String description, int type) {
        super(description, type);
    }

    /**
     * outputs all the information pertaining the task in a specified format for storage purposes
     * @return a string containing all important details of the task
     */
    public String storageDisplay() {
        return "T" + super.storageDisplay();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
