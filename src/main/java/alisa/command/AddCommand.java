package alisa.command;

import alisa.*;
import alisa.task.Task;
import alisa.task.TaskList;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.addTask(task);
        ui.showMessage(message);
        storage.syncFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
