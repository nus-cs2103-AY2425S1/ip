package handsome.command;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;

/**
 * The ByeCommand class handles the "bye" command, which
 * exits the Handsome chatBot. When executed, this command displays
 * a goodbye message to the user and ends the chatBot session.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showByeText();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
