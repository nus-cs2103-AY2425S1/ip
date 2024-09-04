package snowy.tasklist;

/**
 * Todo class is a task that has a description.
 *
 * The Todo class extends the Task class and adds a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}