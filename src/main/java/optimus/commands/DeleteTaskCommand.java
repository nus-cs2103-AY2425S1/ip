package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

/**
 * Command to delete task from storage
 */
public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from permanent and session storage and prints to UI if successful
     *
     * @param storage - permanent storage
     * @param tasks   - session storage
     * @return - Success message
     * @throws InvalidTaskNumberException - when task number does not exist or is not valid
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws InvalidTaskNumberException {
        Task removed = tasks.removeTask(taskIndex);
        storage.rewriteEntireFile(tasks.getList());
        return "Noted. I've removed this task:\n" + removed + "\n" + String.format("Now you have %d tasks in the list",
                tasks.getNumOfTasks());
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
