package TheBotFather.Command;

import TheBotFather.util.Storage;
import TheBotFather.util.TaskList;
import TheBotFather.util.TheBotFatherException;
import TheBotFather.util.Ui;

public class PrintTaskListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printTaskList(taskList.getListDesc());
        return;
    }
}
