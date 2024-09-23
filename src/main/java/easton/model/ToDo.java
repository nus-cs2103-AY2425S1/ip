package easton.model;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {


    /**
     * Constructs a new todo task with the specified description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getCsvFormat() {
        return "T," + super.getCsvFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
