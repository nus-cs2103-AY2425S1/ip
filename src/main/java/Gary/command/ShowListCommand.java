package Gary.command;

import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;

/**
 * The {@code ShowListCommand} class represents a command to display all tasks in the task list.
 * It extends the {@code Command} class and implements the behavior for showing the list of tasks.
 */
public class ShowListCommand extends Command {

    /**
     * Executes the show list command, which displays all tasks in the {@code TaskList}.
     *
     * @param taskList The {@code TaskList} object containing tasks to be displayed.
     * @param ui The {@code Ui} object for user interaction, used to display the tasks.
     * @param storage The {@code Storage} object for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskLists(taskList);
    }

    /**
     * Indicates that this command is not a "bye" command.
     *
     * @return {@code false} as this is not a "bye" command.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
