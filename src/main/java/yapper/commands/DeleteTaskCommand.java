package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * Class that represents the task deletion command.
 */
public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        return taskList.deleteTask(parser.getTaskNumber());
    }

    @Override
    public String commandDescription() {
        return "Deletes a task (tasks are 1-indexed), FORMAT: delete [TASK_NUMBER]";
    }

    @Override
    public String toString() {
        return "DeleteTaskCommand";
    }
}
