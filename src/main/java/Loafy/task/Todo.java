package loafy.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs an undone todo task with the specified name.
     *
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a todo task with the specified status (done or undone) and name.
     *
     * @param isDone The state of the task ({@code true} for done and {@code false} for undone).
     * @param name The name of the todo task.
     */
    public Todo(boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Returns a string representation of this todo task for user view.
     * The string representation is in the format "[T][status] name",
     * "[T]" symbolises type of task (todo);
     * status is represented by "X" if task is done and " " if task is undone.
     *
     * @return a string representation of this todo task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this todo task for data storing.
     * The string representation is in the format "type,status,name", where:
     * type = "T";
     * status = "true" if task is done and "false" if task is not done;
     * name is exactly the name of the task;
     *
     * @return a string representation of this todo task.
     */
    public String convertToTxt() {
        return String.format("%s,%s","T", super.convertToTxt());
    }
}
