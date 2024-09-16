package bobby.command;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand implements Command {

    /**
     * Constructs an {@code AddTaskCommand} with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    private Task task;
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command by adding the task to the task list, saving the updated task list to storage,
     * and displaying the task addition message through the UI.
     *
     * @param tasks The current task list to which the task will be added.
     * @param ui The UI used to display messages to the user.
     * @param storage The storage system to save the updated task list.
     * @return A message confirming that the task has been added.
     * @throws BobbyException If there is an issue when adding tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        tasks.add(task);
        storage.saveTasks(tasks);
        return ui.getTaskAddedMessage(this.task, tasks.size());
    }
}
