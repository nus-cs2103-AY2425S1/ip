package friendlybot.task;

/**
 * Represents a friendlybot.task.ToDo task.
 * A friendlybot.task.ToDo task is a simple task with only a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new friendlybot.task.ToDo task with the specified description.
     *
     * @param description The description of the friendlybot.task.ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the friendlybot.task.ToDo task.
     *
     * @return A string in the format "[T][statusIcon] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
