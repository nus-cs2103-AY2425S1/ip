package rose.task;

/**
 * Represents a task without any date/time attached to it.
 *
 * <p>A <code>Todo</code> object is represented by the description of the task,
 * the status of completion, and an optional tag. e.g., <code>[T][ ] watch lecture video</code>.
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone, String tag) {
        super(taskName, isDone, tag);
    }

    public Todo(String taskName, String tag) {
        super(taskName, tag);
    }

    @Override
    public String toString() {
        String tagString = tag.isEmpty() ? "" : " #" + tag;
        return "[T]" + super.toString() + tagString;
    }

    /**
     * Returns a string representation of the todo task in a comma-separated format.
     *
     * <p>The format is: <code>"T,status,taskName"</code>, where <code>status</code>
     * is "X" if the task is done, and a space (" ") if the task is not done.
     * The <code>tag</code> is optional and can be an empty string if the user doesn't give a tag.</p>
     *
     * @return A comma-separated string representing the todo task's type, status, and name.
     */
    @Override
    public String commaString() {
        return String.format("T,%s,%s", super.commaString(), tag);
    }
}
