package choaticbot.tasks;

/**
 * Represents a To-Do task. This class extends the {@code Task} class and implements
 * the specifics for To-Do tasks, including how to represent them in string format for storage.
 */
public class ToDos extends Task {

    /**
     * Constructs a new {@code ToDos} task with the specified name. The task is initialized as incomplete.
     *
     * @param name The name of the To-Do task.
     */
    public ToDos(String name) {
        super(name);
    }

    /**
     * Returns the type of the task as a string. For To-Do tasks, this is "T".
     *
     * @return A string representing the type of the task ("T" for To-Do).
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns additional information about the task. For To-Do tasks, there is no additional information,
     * so this method returns an empty string.
     *
     * @return An empty string since To-Do tasks do not have additional information.
     */
    @Override
    public String getAdditionalInfo() {
        return "";
    }

    /**
     * Converts the To-Do task to a file-friendly string format. This includes the task type, name, and completion status.
     *
     * @return A string formatted for file storage, e.g., "T|task_name|true".
     */
    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete();
    }

    /**
     * Returns a string representation of the To-Do task, including its completion status and name.
     *
     * @return A string in the format {@code [T] task_name}, where [T] indicates the type of task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
