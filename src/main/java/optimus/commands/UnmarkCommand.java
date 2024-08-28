package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    /**
     * Marks the task as incomplete in permanent and session storage, prints to UI if successful
     * @param storage - permanent storage
     * @param tasks - session storage
     * @param ui - user interface
     * @throws InvalidTaskNumberException - when task number does not exist or is not valid
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        Task task = tasks.getTask(taskIndex);

        task.markAsIncomplete();
        storage.rewriteEntireFile(tasks.getList());
        ui.printToInterface("OK, I've marked this task as not done yet:");
        ui.printToInterface(task.toString());
    }

}
