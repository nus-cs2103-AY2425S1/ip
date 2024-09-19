package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * Handles the "find" command.
 */
public class FindCommand implements Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.isEmpty()) {
            throw new XBotException("Mmmm...please enter something for me to find! >.<");
        }
        return list.findTask(rest);
    }
}
