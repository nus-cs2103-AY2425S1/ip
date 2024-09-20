package samson.command;

import java.io.IOException;

import samson.Storage;
import samson.Ui;
import samson.task.TaskList;

/**
 * The <code> MarkCommand</code> class represents a command that marks a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a <code> MarkCommand</code> with the specified index of the task to be marked as done.
     *
     * @param index The 1-based index of the task to be marked as done.
     *              The index is adjusted to zero-based indexing internally.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the command by marking the task at the specified index as done, showing a confirmation message,
     * and saving the updated task list to the storage file.
     *
     * @param taskList The list of tasks in which the task will be marked as done.
     * @param ui       The UI object used to display messages to the user.
     * @param storage  The storage object used to save tasks to the file.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            return ui.showTaskNumInvalid();
        }
        taskList.markTask(index);
        storage.saveTasksToFile(taskList.getTasks());
        return ui.showTaskMarked(taskList.get(index));
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false because the MarkCommand does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
