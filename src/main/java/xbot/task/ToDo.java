package xbot.task;

/**
 * Represents a to-do task in the XBot application.
 * A {@code ToDo} task has a description.
 */
public class ToDo extends Task {

    /**
     * Constructs the {@code ToDo} task with the specified description.
     * @param description The description of the task. 
     */
    public ToDo(String description) {
        super(description, TaskType.T);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
