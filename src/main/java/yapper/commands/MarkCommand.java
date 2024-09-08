package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * A class representing the command to mark tasks.
 */
public class MarkCommand extends Command {
    public MarkCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        return taskList.markOrUnmarkTask("mark", parser.getTaskNumber());
    }
}
