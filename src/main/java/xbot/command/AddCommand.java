package xbot.command;

import xbot.TaskList;
import xbot.exception.UnknownTaskTypeException;
import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.task.Deadline;
import xbot.task.Event;
import xbot.task.Task;
import xbot.task.ToDo;
import xbot.ui.Ui;

/**
 * Handles the "add" command.
 *
 * This is the parent class for TodoCommand, EventCommand, DeadlineCommand.
 */
public class AddCommand implements Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.isEmpty()) {
            throw new XBotException("I cannot add an empty description >.<");
        } else {
            throw new XBotException("Hmm...This is not a valid command :0");
        }
    }
}