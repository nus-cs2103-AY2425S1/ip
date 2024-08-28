package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.tasks.Task;

public class AddTaskCommand extends Command {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to both permenant and session storage and prints success message
     * @param storage - permenant storage
     * @param tasks - session storage
     * @param ui - user interface
     */
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
