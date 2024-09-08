package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;

/**
 * A class representing the list command to list out tasks.
 */
public class ListOutTasksCommand extends Command {
    public ListOutTasksCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) {
        return taskList.listTasks();
    }
}
