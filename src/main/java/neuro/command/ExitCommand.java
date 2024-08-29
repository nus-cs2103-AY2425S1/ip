package neuro.command;

import neuro.Ui;
import neuro.Storage;

import neuro.task.TaskList;

/**
 * The {@code ExitCommand} class represents a command to exit the Neuro application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.updateTaskFile(tasks);
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
