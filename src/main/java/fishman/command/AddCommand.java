package fishman.command;

import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * Represent the command to add a new task to the task list.
 * This command implements the Command interface and is for
 * adding a single task to the task list and returns the confirmation message
 * that the task has been successfully added to the user and the number of
 * task in the task list.
 */
public class AddCommand implements Command {
    private final Task task;

    /**
     * Constructs a AddCommand with the specified task.
     *
     * @param task The task object to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc
     *
     *      Adds the task to the task list and returns a confirmation message alongside the
     *      current number of tasks in the list.
     *
     * @param taskList The TaskList which the new task will be added.
     * @param ui The Ui instance to generate the confirmation message.
     * @return The confirmation message indicating the command execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        return ui.getAddedTaskMessage(task, taskList.size());
    }
}
