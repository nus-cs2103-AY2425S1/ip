package darkpool.command;

import darkpool.task.Task;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;

public class AddCommand extends Command {

    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.add(String.valueOf(task), taskList.getSize());
    }

}
