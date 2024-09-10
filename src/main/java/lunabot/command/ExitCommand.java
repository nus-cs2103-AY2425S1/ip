package lunabot.command;

import lunabot.storage.Storage;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

}
