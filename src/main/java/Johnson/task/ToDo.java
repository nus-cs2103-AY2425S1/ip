package Johnson.task;

/**
 * Represents a task with no date/time attached to it.
 */
public class ToDo extends Task{
    public ToDo() {
        super();
    }

    public ToDo(String task, String... tags) {
        super(task, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
