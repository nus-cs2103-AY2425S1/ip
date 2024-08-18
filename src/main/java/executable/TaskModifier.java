package executable;

import java.util.ArrayList;

import task.Task;

/**
 * An abstract class for all executables that can modify tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public abstract class TaskModifier extends Executable {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for a new TaskModifier.
     */
    public TaskModifier() {
        this.tasks = null;
    }

    /**
     * Constructor for a new TaskModifier.
     *
     * @param tasks the tasks to modify.
     */
    public TaskModifier(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Change the task list to the given tasks.
     *
     * @param tasks the new task list.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
