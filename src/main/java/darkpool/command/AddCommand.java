package darkpool.command;

import darkpool.gui.Gui;
import darkpool.task.Task;
import darkpool.util.Storage;
import darkpool.tasklist.TaskList;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) {
        taskList.addTask(task);
        return gui.add(String.valueOf(task), taskList.getSize());
    }

}
