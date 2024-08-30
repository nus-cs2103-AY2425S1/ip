package krona.task;

/**
 * Represents a normal task with no due date.
 */
public class ToDo extends Task {

    /**
     * Contructs a new ToDo task.
     *
     * @param description The description os the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
