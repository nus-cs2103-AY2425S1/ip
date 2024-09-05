package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * The type List command.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }
}
