package killua.command;

import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

