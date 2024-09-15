package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * Class that represents the clear task list command.
 */
public class ClearTaskListCommand extends Command {
    public ClearTaskListCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        return taskList.clearTasks();
    }

    @Override
    public String commandDescription() {
        return "Clears all tasks from the task list, FORMAT: clear";
    }

    @Override
    public String toString() {
        return "ClearTaskListCommand";
    }
}
