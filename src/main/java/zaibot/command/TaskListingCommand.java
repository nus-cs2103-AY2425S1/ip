package zaibot.command;

import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;

/**
 * This class represents the command used for listing all the tasks.
 */
public class TaskListingCommand extends Command {
    public TaskListingCommand() {
        super("list", null);
    }

    @Override
    public String runCommandSpecificLogic(TaskList tasks, Storage storage) {
        return Ui.printTaskList(tasks);
    }
}
