package bimo.command;

import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Represents all invalid commands.
 */
public class HelpCommand extends Command {
    /**
     * Returns the list of commands available.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot when there is invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.printInvalidCommandMessage();
        return response;
    }
}
