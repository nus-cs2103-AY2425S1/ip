package echo.task;

/**
 * The Todo class represents a basic task without any additional information.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
    /**
     * Returns a string representing the todo task's save format,
     * intended for saving to a file.
     *
     * @return a string representing the todo task's data
     */
    @Override
    public String getData() {
        return super.getData();
    }
}