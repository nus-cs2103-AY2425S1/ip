package duck.commands;

import duck.data.TaskList;
import duck.storage.Storage;
import duck.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
