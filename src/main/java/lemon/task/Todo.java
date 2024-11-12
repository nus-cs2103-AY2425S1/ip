package lemon.task;
/**
 * Represent the to do tasks
 * @author He Yiheng
 */
public class Todo extends Task {
    /**
     * Constructor for the to do tasks
     * @param description description of the task
     * @param isDone whether the task is completed
     */
    public Todo(String description, boolean isDone) {
        super(description, "todo", isDone);
    }

    @Override
    public String toFileString() {
        return "T|" + isDone + "|" + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
