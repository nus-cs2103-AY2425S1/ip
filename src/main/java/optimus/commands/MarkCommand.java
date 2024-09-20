package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

/**
 * Command to mark task as complete
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task as complete in permanent and session storage, prints to UI if successful
     *
     * @param storage - permanent storage
     * @param tasks   - session storage
     * @throws InvalidTaskNumberException - when task number does not exist or is not valid
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws InvalidTaskNumberException {
        Task task = tasks.getTask(taskIndex);

        task.markAsComplete();
        storage.rewriteEntireFile(tasks.getList());
        return "Nice! I've marked this task as complete:\n" + task;
    }

    /**
     * @return
     */
    public int getTaskIndex() {
        return taskIndex;
    }
}
