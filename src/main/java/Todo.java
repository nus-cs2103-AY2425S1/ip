/**
 * The class represents a simple task that does not have any specific
 * date or time constraints. It extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo, including its status icon and description.
     *
     * @return A string in the format "[T][statusIcon] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
