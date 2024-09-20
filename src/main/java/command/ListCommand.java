package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
