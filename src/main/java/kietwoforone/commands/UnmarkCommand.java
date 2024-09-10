package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public class UnmarkCommand extends Command {

    private int position;

    public UnmarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        String unmarkedTask = tasks.unmarkTask(this.position);
        ui.showUnmarkTask(unmarkedTask);
        try {
            storage.saveFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Task unmarked";
    }

}
