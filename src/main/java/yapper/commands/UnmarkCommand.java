package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * A class representing the command to unmark tasks.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        return taskList.markOrUnmarkTask("unmark", parser.getTaskNumber());
    }

    @Override
    public String commandDescription() {
        return "Unmarks a task (tasks are 1-indexed), FORMAT: unmark [TASK_NUMBER]";
    }

    @Override
    public String toString() {
        return "UnmarkCommand";
    }
}