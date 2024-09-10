package bean.task;

/**
 * Represents a todo task. Inherits from the Task class.
 */
public class TodoTask extends Task {

    /**
     * Initializes a TodoTask with the specified name.
     *
     * @param name The name of the todo task.
     */
    public TodoTask(String name) {
        super(name);
    }


    /**
     * Returns the details specific to the task type. For todo tasks, this is an empty string.
     *
     * @return An empty string since todo tasks have no additional details.
     */
    @Override
    public String getDetails() {
        return "";
    }

    /**
     * Returns the format in which the task is saved to the file.
     *
     * @return A string representing the save format of the todo task.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Returns a string representation of the task for display purposes.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + getDetails();
    }
}
