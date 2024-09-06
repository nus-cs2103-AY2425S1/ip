package lolo.command;

import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command removes the specified task and returns a confirmation message.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command by deleting the task from the task list,
     * and returns a confirmation message that the task has been deleted.
     * Also, saves the updated task list to storage.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param storage The storage to save the updated task list.
     * @return A string message confirming the deletion of the task.
     * @throws LoloException If there is an error during execution or saving.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws LoloException {
        Task task = tasks.deleteTask(taskNumber);
        storage.save(tasks.getTasks());
        return "Noted. I've removed this task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

