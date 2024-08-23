package zaibot.command;

import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;

public class TaskListingCommand extends Command {
    public TaskListingCommand() {
        super("list", null);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) {
        Ui.printTaskList(tasks);
    }
}
