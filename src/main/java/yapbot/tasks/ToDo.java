package yapbot.tasks;

/**
 * Child class of Task with no additional fields.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveTask() {
        return "T/" + super.saveTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
