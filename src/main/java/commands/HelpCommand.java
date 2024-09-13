package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Command to show help information for all available commands.
 */
public class HelpCommand implements Command {

    /**
     * Executes the command to show help information.
     *
     * @param tasks   TaskList (not used in this command).
     * @param ui      UI to handle user interaction.
     * @param storage Storage (not used in this command).
     * @return Help information as a string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpMessage();
    }
}
