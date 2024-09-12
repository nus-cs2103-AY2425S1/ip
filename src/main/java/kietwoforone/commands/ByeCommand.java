package kietwoforone.commands;

import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye";
    }

}
