package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

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
