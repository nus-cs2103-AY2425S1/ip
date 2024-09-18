package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ResetCommand extends Command {
    public static final String COMMAND = "reset";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        tasks.reset();
        ui.printWithFormat("OK, I've removed all your tasks.");
    }
}
