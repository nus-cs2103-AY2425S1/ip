package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * Represents the command where the user is asking for help regarding the commands of the application.
 * This command shows the user all valid commands and their respective formats
 */
public class HelpCommand extends Command {

    /**
     * Executes the Help command and returns the help message to the user
     *
     * @param tasks   The TaskList to which the new Event task should be added.
     * @param ui      The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     * @return String returns the help message to be displayed to the User
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpMessage();
    }

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean showExitStatus() {
        return false;
    }
}
