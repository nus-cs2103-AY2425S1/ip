package Commands;

import Exceptions.KieTwoForOneException;
import Storage.Storage;
import Tasks.TaskList;
import UI.UI;

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

}
