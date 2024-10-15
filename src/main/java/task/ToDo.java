package task;

/**
 * The ToDo class represents a task that needs to be done without any specific deadline or event duration.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param desc The description of the task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns the type of the task as a string. For a ToDo task, this is always "[T]".
     *
     * @return A string representing the type of task, which is "[T]" for ToDo tasks.
     */
    @Override
    public String getType() {
        return "[T]";
    }
}
