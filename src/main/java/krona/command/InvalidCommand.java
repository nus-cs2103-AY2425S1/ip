package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}