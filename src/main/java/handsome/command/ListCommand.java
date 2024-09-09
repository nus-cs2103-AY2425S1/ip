package handsome.command;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;

/**
 * The ListCommand class handles the "list" command, which displays all
 * the tasks currently stored in the Handsome chatBot's task list to the user.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException {
        return ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
