package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from permanent and session storage and prints to UI if successful
     * @param storage - permanent storage
     * @param tasks - session storage
     * @param ui - user interface
     * @throws InvalidTaskNumberException - when task number does not exist or is not valid
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        Task removed = tasks.removeTask(taskIndex);
        storage.rewriteEntireFile(tasks.getList());
        ui.printToInterface("Noted. I've removed this task:");
        ui.printToInterface(removed.toString());
        ui.printToInterface(String.format("Now you have %d tasks in the list", tasks.getNumOfTasks()));
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
