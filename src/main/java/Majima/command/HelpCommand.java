package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class HelpCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
