/**
 * Represents a Todo task.
 * Inherits from the Task class and is used for tasks that do not have deadlines or specific time frames.
 */
package bangmang.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string with the format specific to Todo tasks, including the task type.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
