package spike.commands;

import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to list tasks by date.
 */
public class ListByDateCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandType() {
        return "List By Date";
    }

    /**
     * Lists all tasks by date.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface that interacts with the user.
     * @param storage The storage to be updated.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.listTasksByDate());
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
