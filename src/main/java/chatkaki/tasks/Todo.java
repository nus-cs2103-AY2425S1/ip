package chatkaki.tasks;


/**
 * Represents a todo task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Formats the todo task to be saved in the file.
     * @return The formatted todo task.
     */
    @Override
    public String fileFormat() {
        return "TODO," + super.fileFormat();
    }

    /**
     * Formats the todo task to be displayed to the user.
     * @return The formatted todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
