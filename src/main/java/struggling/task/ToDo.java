package struggling.task;

/**
 * ToDo class stores the description and
 * the completion state of a task.
 */
public class ToDo extends Task {

    /**
     * Initializes a ToDo object.
     *
     * @param description Description of Task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getState() {
        return String.format("T | %s", super.getState());
    }
}
