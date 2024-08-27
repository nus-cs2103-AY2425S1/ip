package command;

import ui.Ui;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command to display all tasks in the task list.
     *
     * @param tasks Task list to display tasks from.
     * @param ui Ui to display the task list.
     * @param storage Storage to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}