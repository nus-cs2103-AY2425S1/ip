package wiggly.task;

/**
 * A class representation of a to-do task with no deadline
 */
public class ToDo extends Task {

    /**
     * Creates a {@code ToDo} instance containing the description
     * @param description The description of the {@code ToDo} instance
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T|" + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
