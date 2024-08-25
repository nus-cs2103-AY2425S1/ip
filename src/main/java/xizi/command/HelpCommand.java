package xizi.command;

import xizi.Storage;
import xizi.task.TaskList;
import xizi.Ui;

public class HelpCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.printHelp();
    }
}

