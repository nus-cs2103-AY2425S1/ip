package milo.command;

import milo.tasks.TaskList;
import milo.tasks.TaskTypes;
import milo.ui.Ui;

public class InvalidCommand extends Command{

    @Override
    public void execute(TaskList taskList) {
        return;
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return ui.printError(TaskTypes.TaskType.INVALID, "");
    }
}
