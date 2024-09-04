package guy.tasks;

/**
 * Representation of a task to be done.
 * No dates attached.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     *
     * @param name description of the task
     */
    public ToDo(String name) {
        super(name, TaskType.TODO);
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }
}
