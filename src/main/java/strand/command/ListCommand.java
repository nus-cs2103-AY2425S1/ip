package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;

/**
 * The {@code ListCommand} class represents a command to display the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by showing the current list of tasks to the user.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The {@code Ui} object used to display messages to the user.
     * @param storage The {@code Storage} object responsible for saving/loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks.toString());
    }
}
