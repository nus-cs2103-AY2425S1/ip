package matcha.command;

import matcha.tasklist.TaskList;

import matcha.storage.Storage;

import matcha.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListTaskCommand extends Command{

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks The task list to list all tasks from.
     * @param ui Ui object to interact with user.
     * @param storage Storage object to save tasks to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
