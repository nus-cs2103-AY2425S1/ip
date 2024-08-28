package justbot.command;

import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeMessage();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public Task getTask() {
        return null;
    }

}
