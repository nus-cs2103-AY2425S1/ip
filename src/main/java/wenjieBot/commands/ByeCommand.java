package wenjieBot.commands;

import wenjieBot.Storage;
import wenjieBot.TaskList;
import wenjieBot.Ui;

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
