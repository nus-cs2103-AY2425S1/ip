package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;

/**
 * The {@code ByeCommand} class handles the command to exit the application.
 * It provides methods to indicate that the application should terminate and to end the user interface conversation.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.endConversation();
    }
}
