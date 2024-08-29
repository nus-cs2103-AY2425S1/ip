package lict.task;

/**
 * The {@code lict.task.Todo} class represents a task that has a description and can be marked as done or not done.
 * It extends the {@code lict.task.Task} class and provides specific implementations for the {@code toString} and {@code toData} methods.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("TODO | %s | %s\n", status, this.description);
    }

    /**
     * Loads a {@code Todo} task from a string containing task data.
     *
     * @param dataMessage The string containing the data of the todo task.
     * @return A {@code Todo} object created from the data string.
     */
    public static Todo loadTask(String dataMessage) {
        return new Todo(dataMessage);
    }
}
