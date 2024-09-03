package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

public class markCommand extends Command {
    private final int taskIndex;
    public markCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(taskIndex).mark();
        ui.printMark(taskList.getTask(taskIndex));
        storage.saveTasks(taskList.getTaskList());
    }
}
