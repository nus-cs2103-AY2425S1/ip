package tasks;

/**
 * ToDo class to store attributes and methods.
 */
public class ToDo extends Task {

    /**
     * Constructs ToDo object.
     *
     * @param description Description of the ToDo Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
