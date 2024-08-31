/**
 * Represents a ToDos task.
 */
public class ToDos extends Task {

    /**
     * Constructor for ToDos.
     * @param description the description of the task
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructor for ToDos.
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
