package bro.task;

/**
 * Represents a Todo task tracked by Bro. A <code>TodoTask</code> object
 * is a type of task that has no specific date or time associated with it.
 */
public class TodoTask extends Task {

    /**
     * Constructs a new TodoTask with the specified content.
     * The task is initially marked as not completed.
     *
     * @param content The content of the Todo task.
     */
    public TodoTask(String content) {
        super(content);
    }

    /**
     * Constructs a new TodoTask with the specified content and completion status.
     *
     * @param content     The content of the Todo task.
     * @param isCompleted The initial completion status of the task.
     */
    public TodoTask(String content, boolean isCompleted) {
        super(content, isCompleted);
    }

    /**
     * Returns the string representation of the Todo task,
     * indicating it is a Todo task along with its content
     * and completion status.
     *
     * @return A string representation of the Todo task, prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
