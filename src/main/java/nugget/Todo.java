package nugget;

/**
 * Represents a Todo task, which is a type of Task without a specific date or time.
 * This class extends the Task class and is marked as a Todo in its string representation.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task type of the Todo.
     * The type is represented as "[T]" for Todo.
     *
     * @return A string representing the task type as "[T]".
     */
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Returns the string representation of the Todo task.
     * This includes the task type "[T]" followed by the description from the superclass.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return this.getTaskType() + super.toString();
    }
}
