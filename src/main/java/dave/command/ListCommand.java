package dave.command;

import dave.task.TaskList;
import dave.storage.Storage;
import dave.ui.Ui;

/**
 * Represents the command to list all tasks in the task list.
 * This command displays all the tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method outputs all the tasks in the task list, numbered sequentially.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be displayed.
     * @param storage The {@code Storage} object to handle saving the task list (not used in this command).
     * @param ui      The {@code Ui} object to handle user interaction (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }
}
