package neuro.command;

import neuro.Ui;
import neuro.Storage;

import neuro.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
