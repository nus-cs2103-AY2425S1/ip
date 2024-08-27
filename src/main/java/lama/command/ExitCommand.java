package lama.command;

import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represents a command to exit the application.
 * Subclass of command class.
 */
public class ExitCommand extends Command {

    /**
     * Run the command to exit the application.
     * @param taskList Task list, although not used in this command.
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
