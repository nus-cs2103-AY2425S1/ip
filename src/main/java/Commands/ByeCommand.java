package Commands;

import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.displayResponse("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
