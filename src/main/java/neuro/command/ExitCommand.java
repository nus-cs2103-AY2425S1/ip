package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

/**
 * The {@code ExitCommand} class represents a command to exit the Neuro application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.updateTaskFile(tasks);
        return "Goodbye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
