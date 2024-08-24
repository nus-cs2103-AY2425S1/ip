package Spongebob.command;

import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.unknownCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"unknown"};
    }
}
