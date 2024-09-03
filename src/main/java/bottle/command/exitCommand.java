package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

public class exitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMsg();
        System.exit(0);
    }
}
