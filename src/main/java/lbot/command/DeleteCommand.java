package lbot.command;

import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;
import lbot.task.Task;

/**
 * This class deletes tasks.
 */
public class DeleteCommand extends Command {
    private int taskID;

    /**
     * Public constructor for DeleteCommand.
     *
     * @param taskID is the listed id of the task to be deleted.
     */
    public DeleteCommand(int taskID) {
        this.taskID = taskID - 1;
    }

    /**
     * Deletes the specified task.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @return ui method containing response.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        if (taskID > tasks.getTaskCount()) {
            throw new ExecuteCommandException("Task ID out of range.");
        }
        if (taskID < 0) {
            throw new ExecuteCommandException("Task ID must be positive.");
        }
        Task task = tasks.getTask(taskID);
        tasks.deleteTask(taskID);
        storage.saveTaskToFile(tasks);
        return ui.sayDeleted(task);
    }

    @Override
    public String toString() {
        return "del command " + taskID;
    }
}
