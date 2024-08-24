package Spongebob.command;

import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;

public class DisplayCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"display"};
    }
}
