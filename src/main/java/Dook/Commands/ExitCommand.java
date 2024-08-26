package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException,IOException {
        storage.write(tasks);
        ui.exit();
        super.isExit = true;
    }
}
