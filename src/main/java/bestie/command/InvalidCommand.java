package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidCommand();
    }
}
