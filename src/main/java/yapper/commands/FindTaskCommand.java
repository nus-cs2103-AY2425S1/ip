package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * A class that represents the find command for finding a task with a given keyword in the task list.
 */
public class FindTaskCommand extends Command {
    public FindTaskCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        return taskList.findTasks(parser.getKeyword());
    }

    @Override
    public String commandDescription() {
        return "Finds a task based on a keyword, FORMAT: find [KEYWORD]";
    }

    @Override
    public String toString() {
        return "FindTaskCommand";
    }
}
