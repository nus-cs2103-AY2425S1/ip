package optimus.commands;

import optimus.commands.Command;
import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        Task task = tasks.getTask(taskNum);

        task.markAsIncomplete();
        storage.rewriteEntireFile(tasks.getList());
        ui.printToInterface("OK, I've marked this task as not done yet:");
        ui.printToInterface(task.toString());
    }
}
