package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.commands.Command;
import optimus.tasks.Task;

public class AddTaskCommand extends Command {

    private final Task task;
    public AddTaskCommand(Task task){
        this.task = task;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        storage.appendToStorage(task.getStorageString());
        ui.printToInterface("Got it. I've added this task:");
        ui.printToInterface(task.toString());
        ui.printToInterface(String.format("Now you have %d tasks in the list", tasks.getNumOfTasks()));
    }

    public Task getTask() {
        return task;
    }
}
