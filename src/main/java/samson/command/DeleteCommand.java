package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.Task;
import samson.task.TaskList;

import java.io.IOException;

/**
 * The <code> DeleteCommand </code> class represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a <code> DeleteCommand </code>  with the specified index of the task to be deleted.
     *
     * @param index The 1-based index of the task to be deleted.
     *              The index is adjusted to zero-based indexing internally.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * showing a confirmation message, and saving the updated task list to the storage file.
     *
     * @param taskList The list of tasks from which the task will be deleted.
     * @param ui       The UI object used to display messages to the user.
     * @param storage  The storage object used to save tasks to the file.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            return ui.showTaskNumInvalid();
        }
        Task removedTask = taskList.deleteTask(index);
        storage.saveTasksToFile(taskList.getTasks());
        return ui.showTaskDeleted(removedTask, taskList);

    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false because the DeleteCommand does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
