package main.java.commands;

import main.java.tasks.Task;
import main.java.tasks.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

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
