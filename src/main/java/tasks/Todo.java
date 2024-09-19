package tasks;

/**
 * Represents a simple to-do task with a name and a completion status.
 * This class extends the Task class to provide specific functionality
 * for tasks that don't have a start or end date.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified name.
     *
     * @param task the name of the to-do task
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the to-do task, including its completion status.
     * The format is "[x] taskName" if the task is done, and "[ ] taskName" if not.
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return String.format("%s ", super.toString());
    }

    /**
     * Converts the to-do task into a string format suitable for saving to a file or database.
     * The format is "td,taskName,completionStatus", where completionStatus is "y" if the task is done,
     * and "n" if it is not done.
     *
     * @return a string representation of the to-do task in the format "td,taskName,completionStatus"
     */
    @Override
    public String toSaveFormat() {
        return String.format("td,%s ", super.toSaveFormat());
    }
}
