package winner;

/**
 * Represents a ToDo task which includes the description of the task.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of a ToDo task, including its completion status and description.
     *
     * @return A String representing the ToDo task, including its completion status and description.
     */
    @Override
    public String taskToString() {
        if (isDone) {
            return "[T] [X] " + description;
        }
        return "[T] [ ] " + description;
    }

    /**
     * Marks a ToDo task as done and returns a String confirming the task has been marked as done.
     *
     * @return A String indicating that the task has been marked as done.
     */
    @Override
    public String markDone() {
        return super.markDone() + "\n" + " ".repeat(5) + "[T] [X] " + description;
    }

    /**
     * Marks a ToDo task as undone and returns a String confirming the task has been marked as undone.
     *
     * @return A String indicating the task has been marked as undone.
     */
    @Override
    public String unmarkDone() {
        return super.unmarkDone() + "\n" + " ".repeat(5) + "[T] [ ] " + description;
    }

    /**
     * Deletes a ToDo task and returns a String confirming the task has been deleted.
     *
     * @return A String indicating that the task has been deleted.
     */
    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n" + " ".repeat(5) + "[T] [X] " + description;
        }
        return super.deleteTask() + "\n" + " ".repeat(5) + "[T] [ ] " + description;
    }

}
