package niko.command;

import niko.common.NikoException;
import niko.main.Storage;
import niko.main.Ui;
import niko.task.Task;
import niko.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (1-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the task at the specified index,
     * updating the UI, and saving the updated task list to storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save the updated task list.
     * @return
     * @throws NikoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        Task removedTask = tasks.deleteTask(index - 1);
        String response = ui.showDeleteTaskMessage(removedTask, tasks.getTaskCount());
        String readyToWrite = tasks.getTasks().toString();
        storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));
        return response;
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return {@code false}, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
