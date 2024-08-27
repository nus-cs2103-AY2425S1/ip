package lama.command;

import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represent a command to list down the task list.
 * Subclass of command class.
 */
public class ExitCommand extends Command {

    /**
     * Run the command by listing down the current task list.
     * @param taskList Task list which will be listed.
     * @param storage Storage used to save or load tasks, although not used in this command.
     * @param ui User interface that interacts with user.
     */
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) {
        ui.showExitCommand();
    }

    /**
     * Indicates that this command will exit the application.
     * @return true, as this command will exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
