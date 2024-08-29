package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Task;

public class DeleteCommand extends Command {
    private int taskID;

    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Task task = tasks.getTask(taskID);
        tasks.deleteTask(taskID);
        storage.saveTaskToFile(tasks);
        ui.printTaskDeletedMessage(task);
    }

    @Override
    public String toString() {
        return "del command " + taskID;
    }
}
