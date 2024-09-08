package noosy.commands;

import noosy.storage.Storage;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents the list command in the Noosy task management chatbot.
 * This command is responsible for listing all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Displays all tasks in the task list.
     *
     * @param tasks   The current list of tasks (not used in this command).
     * @param ui      The user interface for displaying output.
     * @param storage The storage for persisting tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
