package src.commands;

import src.Storage;
import src.TaskList;
import src.Ui;

public class ByeCommand extends Command {

    public ByeCommand(boolean isExit, String input) {
        super(true, input);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeTasks();
        ui.showFarewell();
        super.setIsExit(true);
    }
}
