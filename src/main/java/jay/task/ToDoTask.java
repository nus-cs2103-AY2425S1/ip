package jay.task;

/**
 * Represents a to-do task.
 */
public class ToDoTask extends Task {
    public ToDoTask(String description, boolean isDone, Priority priority) {
        super(description, isDone, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSimpleFormat() {
        return "T | " + super.getSimpleFormat();
    }
}
