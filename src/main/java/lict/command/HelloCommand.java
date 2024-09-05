package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;

/**
 * The {@code HelloCommand} class handles the command to start the conversation with the user.
 * It provides methods to initiate the user interface interaction when the application begins.
 */
public class HelloCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.startConversation();
    }
}
