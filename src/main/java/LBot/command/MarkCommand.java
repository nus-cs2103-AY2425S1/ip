package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Task;

public class MarkCommand extends Command {
    private int taskID;

    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Task task = tasks.getTask(taskID);
        tasks.markTask(taskID);
        storage.saveTaskToFile(tasks);
        ui.printTaskMarkedMessage(task);
    }

    @Override
    public String toString() {
        return "mark command " + taskID;
    }
}
