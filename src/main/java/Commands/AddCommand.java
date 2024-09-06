package Commands;

import Exceptions.KieTwoForOneException;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import UI.UI;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        tasks.addTasks(this.task);
        ui.showAddTasks(tasks.getTaskList(), this.task);
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
