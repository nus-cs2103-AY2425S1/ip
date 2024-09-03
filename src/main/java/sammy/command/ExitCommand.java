package sammy.command;

import sammy.Storage;
import sammy.TaskList;
import sammy.Ui;
import sammy.Command;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

