/**
 * These are tasks without any date or time attached to them
 */
public class TodoTask extends Task {
    /**
     * Constructor for todo task
     * @param description description of task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructor for todo task
     *
     * @param description description of task
     * @param isDone status of task
     */
    public TodoTask(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Overrides string representation to show more complete information of TodoTask
     *
     * @return string representation of TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
