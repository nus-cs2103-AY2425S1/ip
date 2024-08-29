package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Task;

/**
 * This class marks tasks as complete or incomplete.
 */
public class MarkCommand extends Command {
    private int taskID;

    /**
     * Public constructor for MarkCommand
     *
     * @param taskID is the listed id of the task to be marked.
     */
    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Marks the specified task.
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
        tasks.markTask(taskID);
        storage.saveTaskToFile(tasks);
        ui.printTaskMarkedMessage(task);
    }

    @Override
    public String toString() {
        return "mark command " + taskID;
    }
}
