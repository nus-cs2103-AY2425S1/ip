package tasks;

/**
 * ToDo class to store attributes and methods.
 */
public class ToDo extends Task {

    /**
     * Constructor for Deadline class.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritDoc.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
