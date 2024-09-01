package Karen.commands;

import Karen.tasks.Task;
import Karen.tasks.TaskList;
import Karen.util.Storage;
import Karen.util.Ui;

public class AddTaskCommand extends Command{
    private Task task;

    public AddTaskCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.task);
        ui.showAddTaskMessage(this.task, taskList);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
