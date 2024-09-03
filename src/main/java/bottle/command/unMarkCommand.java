package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

public class unMarkCommand extends Command {
    private final int taskIndex;

    public unMarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(taskIndex).unMark();
        ui.printUnMark(taskList.getTask(taskIndex));
        storage.saveTasks(taskList.getTaskList());
    }
}
