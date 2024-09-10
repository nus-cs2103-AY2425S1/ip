package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.Task;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public class DeleteCommand extends Command {

    int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        Task deletedTask = tasks.deleteTask(this.position);
        ui.showDeleteTask(tasks.getTaskList(), deletedTask);
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
        return "Task deleted";
    }

}
