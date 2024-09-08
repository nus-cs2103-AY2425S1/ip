package LunaBot.command;

import LunaBot.storage.Storage;
import LunaBot.task.TaskList;
import LunaBot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

}
