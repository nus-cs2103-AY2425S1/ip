package justbot.command;

import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.invalidCommandMessage();
    }
}
