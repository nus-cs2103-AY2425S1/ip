package guy.tasks;

/**
 * Abstract representation of a generic task.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with the given name.
     * Can't actually be called since the class is abstract.
     *
     * @param name the name of the task
     */
    public Task(String name, TaskType taskType) {
        this.name = name;
        isDone = false;
        this.taskType = taskType;
    }

    /**
     * Flags the Task as complete.
     */
    public void markComplete() {
        isDone = true;
    }

    /**
     * Flags the Task as incomplete.
     */
    public void markIncomplete() {
        isDone = false;
    }

    /**
     * Returns a symbol representing the Task's completion status.
     *
     * @return "X" symbol if the Task was complete, " " otherwise
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks a Task's completion status.
     *
     * @return completion status of tht Task
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Returns the string representation of the Task for use within ThatOneGuy.
     *
     * @return string representation of the Task
     */
    @Override
    public String toString() {
        return ('[' + getStatus() + ']' + ' ' + name);
    }

    /**
     * Returns the string representation of the Task to be saved to a file.
     *
     * @return string representation of the Task
     */
    public String saveFormat() {
        return (" | " + (isDone ? 1 : 0) + " | " + name);
    }
}
