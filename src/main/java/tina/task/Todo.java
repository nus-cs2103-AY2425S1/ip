package tina.task;

/**
 * Represents a todo task.
 * A <code>Todo</code> object contains a description and a boolean to mark completion.
 */
public class Todo extends Task {

    /**
     * Constructs a new <code>Todo</code> object with the specified description when parsed from user.
     *
     * @param des The description of the task.
     */
    public Todo(String des) {
        super(des);
    }

    /**
     * Constructs a new <code>Todo</code> object with the specified description and completion status
     * when parsed from storage.
     *
     * @param des The description of the task.
     * @param isMark The completion status of the task.
     */
    public Todo(String des, boolean isMark) {
        super(des);
        this.isMark = isMark;
    }

    /**
     * Returns the description of the task, including its type.
     *
     * @return A string representing the task description.
     */
    @Override
    public String getDes() {
        return "[T]" + super.getDes();
    }

    /**
     * Returns a string representation of the task for storage purposes.
     *
     * @return A string representing the task in a format for saving to a file.
     */
    @Override
    public String toString() {
        return String.format("T %d %s", isMark? 1 : 0, des);
    }
}
