package executable;

import task.TaskList;

/**
 * An abstract class for all executables that can modify tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public abstract class TaskModifier implements Executable {
    protected TaskList tasks;

    /**
     * Constructor for a new TaskModifier.
     */
    public TaskModifier() {
        this.tasks = null;
    }

    /**
     * Constructor for a new TaskModifier.
     *
     * @param tasks the TaskList to modify.
     */
    public TaskModifier(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Change the task list to the given tasks.
     *
     * @param tasks the new task list.
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }
}
