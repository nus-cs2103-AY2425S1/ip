package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code HelpCommand} class represents a command that, when executed, displays a list of
 * available commands and their descriptions to the user. This command is used to assist the user
 * in understanding how to interact with the application.
 */
public class HelpCommand implements Command {

    /**
     * Executes the Help command by displaying the list of available commands and their
     * descriptions via the user interface.
     *
     * @param storage The storage handler. Not used in this implementation.
     * @param tasks   The list of tasks currently in memory. Not used in this implementation.
     * @param ui      The UI handler used for interacting with the user.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.displayHelp();
    }
}
