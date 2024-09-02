package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
