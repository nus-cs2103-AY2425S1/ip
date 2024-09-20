package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * The type Check clash command.
 */
public class CheckClashCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList clashList = taskList.detectAnomalies();
        return ui.printTaskList(clashList);
    }
}
