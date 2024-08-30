package pixel;

/**
 * Represents a Task that is marked as an action
 */
public class ToDo extends Task {

    /**
     * Constructor method for ToDo
     *
     * @param description name of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo
     * when user types in the command list
     *
     * @return string representation of ToDo for printing
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the ToDo
     * that will be saved to the file
     *
     * @return string representation of ToDo for saving in file
     */
    @Override
    public String getFileString() {
        return String.format("T | %s | %s", getStatusNumber(), getDescription());
    }
}
