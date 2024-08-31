package zbot.task;

/**
 * Represents a task with a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
