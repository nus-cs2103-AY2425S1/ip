package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class HelpCommand implements Command {

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.displayHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
