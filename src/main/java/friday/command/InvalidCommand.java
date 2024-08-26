package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

public class InvalidCommand extends Command {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

