package zaibot.task;

/**
 * This class represents a task with the "to do" category.
 */
public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s",
                    super.toSaveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
