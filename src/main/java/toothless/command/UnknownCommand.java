package toothless.command;

import toothless.command.Command;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

public class UnknownCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.unknownCommand();
    }
}
