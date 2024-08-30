package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

public class PrintTaskListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printTaskList(taskList.getListDesc());
    }
}
