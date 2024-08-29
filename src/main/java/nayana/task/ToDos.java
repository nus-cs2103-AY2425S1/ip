package nayana.task;
/**
 * Represents a task without any deadline attached to it.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the given description.
     * Todos are tasks without any deadline attached to them.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the type of the task as "T ".
     *
     * @return A string representing the task type.
     */
    @Override
    public String getType() {
        return "T ";
    }

    /**
     * Returns a string representation of the ToDos task.
     * The format is "[T] description".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
