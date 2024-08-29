package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

/**
 * The {@code ListCommand} class represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command to list the tasks in the task list.
     *
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("You currently have no tasks.");
        } else {
            ui.showMessage("Here is a list of all your tasks:");
            for (int i = 0; i < tasks.getSize(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }
}
