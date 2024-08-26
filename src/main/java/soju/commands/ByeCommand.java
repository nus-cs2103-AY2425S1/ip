package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

public class ByeCommand extends Command{
    public ByeCommand(String input) {
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
