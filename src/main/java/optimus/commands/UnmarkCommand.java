package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

/**
 * Command to mark task as incomplete
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task as incomplete in permanent and session storage, prints to UI if successful
     *
     * @param storage - permanent storage
     * @param tasks   - session storage
     * @throws InvalidTaskNumberException - when task number does not exist or is not valid
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws InvalidTaskNumberException {
        Task task = tasks.getTask(taskIndex);

        task.markAsIncomplete();
        storage.rewriteEntireFile(tasks.getList());
        return "OK, I've marked this task as not done yet:\n" + task;
    }

}
