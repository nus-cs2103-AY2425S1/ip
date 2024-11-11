package bimo.command;

import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Represents invalid commands.
 */
public class UnknownCommand extends Command {
    /**
     * Informs user that input is invalid and displays
     * list of available commands.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot when there is invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.printUnknownCommandMessage();
        return response;
    }
}
