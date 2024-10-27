package fred.Tasks;

/**
 * The Todo class represents a basic task without any specific time or deadline.
 * It extends the Task class and is used for tasks that need to be done but are not time-bound.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its status and description.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for data storage.
     *
     * @return A string in the format "T | status | description".
     */
    @Override
    public String getDataFormat() {
        return "T" + super.getDataFormat();
    }
}
