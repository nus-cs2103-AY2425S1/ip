package orion.commands;

import orion.tasks.Task;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to add a task to the task list.
 * <p>
 * This command adds a given {@link Task} to the {@link TaskList} and then
 * updates the user interface to reflect this addition.
 * </p>
 */
public abstract class AddTaskCommand extends Command {

    private Task task;

    /**
     * Creates an {@code AddTaskCommand} with the specified task.
     *
     * @param task the {@link Task} to be added to the task list
     */
    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list and updating
     * the user interface.
     *
     * @param tasks  the {@link TaskList} to which the task will be added
     * @param storage the {@link Storage} for managing the task list
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        return String.format("Sure! I've added the following task to your list:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }
}
