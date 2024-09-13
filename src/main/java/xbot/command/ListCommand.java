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
        String output = ("Here are the tasks in your list:\n");
        output = output + ui.showTaskList(list);
        return output;
    }
}
