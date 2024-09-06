package alexer.task;

/**
 * Describes a task in the chatbot.
 *
 * @author sayomaki
 */
public class Task {
    protected String description;

    protected boolean isDone;

    protected TaskType type;

    /**
     * Creates a new task with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        type = TaskType.TASK;
    }

    /**
     * Gets the task description
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task whether it is done.
     *
     * @return "X" if task is done, blank space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done, does not do anything
     * if task is already marked as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks task as done, makes no difference if task
     * is already marked as not done
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Converts the task into a task string to store in the tasks file
     *
     * @return not implemented error as it has to be overriden by the specific task implementation
     */
    public String toTaskString() {
        throw new RuntimeException("Not implemented!");
    }

    /**
     * Converts the task into string form for printing, and
     * primarily focuses on human readability.
     *
     * @return the human-readable string form of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public enum TaskType {
        TASK,
        TODO,
        DEADLINE,
        EVENT
    }
}
