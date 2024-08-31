package park.task;

/**
 * Represents a Task without any deadline or start/end time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo Object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return "T/" + this.getStatusIcon() + "/" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
