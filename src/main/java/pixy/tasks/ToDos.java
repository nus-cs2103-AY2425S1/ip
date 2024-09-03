package pixy.tasks;

/**
 * Represents the Tasks which are ToDo items.
 */
public class ToDos extends Task {

    /**
     * Creates a ToDos object.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
