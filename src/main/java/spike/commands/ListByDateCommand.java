package spike.commands;

import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;

public class ListByDateCommand extends Command {

    @Override
    public String getCommandType() {
        return "List By Date";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.listTasksByDate());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
