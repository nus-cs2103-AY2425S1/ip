package xbot.command;

import xbot.TaskList;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * Handles the "list" command.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) {
        return ui.showTaskList(list);
    }
}
