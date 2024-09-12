package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public class FindDateCommand extends Command {

    private String date;

    public FindDateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        try {
            ui.showSameDate(tasks.getTaskList(), this.date);
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
        return "Date found";
    }

}
