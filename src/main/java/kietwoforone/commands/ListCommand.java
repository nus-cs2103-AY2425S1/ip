package kietwoforone.commands;

import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;
import kietwoforone.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Tasks listed";
    }

}
