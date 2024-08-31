package Edith.command;

import Edith.Ui;
import Edith.Storage;
import Edith.EdithException;
import Edith.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
