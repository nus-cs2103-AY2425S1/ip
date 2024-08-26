package friday.task;

/**
 * Represents a TODO task.
 */
public class Todo extends Task {

    /**
     * Constructs a new TODO task with the specified description.
     *
     * @param description A description of the TODO task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representing the TODO task in a format suitable for file storage.
     *
     * @return A string representing the TODO task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T " + super.toFileFormat();
    }

    /**
     * Returns a string representing the TODO task in a human-readable format.
     *
     * @return A string representing the TODO task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
