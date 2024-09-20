package Johnson.task;

/**
 * Represents a task with no date/time attached to it.
 */
public class ToDo extends Task{
    public ToDo() {
        super();
    }

    /**
     * Constructs a ToDo with the specified task name and optional tags.
     *
     * @param task the name of the task.
     * @param tags the tags of the task.
     */
    public ToDo(String task, String... tags) {
        super(task, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
