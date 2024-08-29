package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Task;

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
        this.taskID = taskID;
    }

    /**
     * Deletes the specified task.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
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
