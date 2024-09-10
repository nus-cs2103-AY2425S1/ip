package Johnson.task;

/**
 * Represents a task with no date/time attached to it.
 */
public class ToDo extends Task{
    public ToDo(String task) {
        super(task);
    }
    public ToDo() {
        super();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
