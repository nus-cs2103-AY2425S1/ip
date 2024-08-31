package mummy.command;

import mummy.task.TaskList;
import mummy.ui.Ui;
import mummy.utility.Storage;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
