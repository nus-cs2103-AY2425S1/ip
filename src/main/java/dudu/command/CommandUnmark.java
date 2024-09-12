package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a unmark task user command into the chatbot
 */
public class CommandUnmark extends Command {
    private int index;

    /**
     * Constructs a CommandUnmark with the specified task.
     *
     * @param index The index of the task to be unmarked.
     */
    public CommandUnmark(int index) {
        this.index = index;
    }

    /**
     * Executes the command by unmarking the task in the task list, updating
     * the user interface with the deleted task, and saving the updated task list to storage.
     *
     * @param taskList The task list on which the command is executed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the task.
     * @throws IOException If there is an error during saving the task to storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        try {
            Task unmarkedTask = taskList.unmarkTask(this.index);
            storage.rewriteFile(taskList);
            return ui.unmarkTask(unmarkedTask);
        } catch (IndexOutOfBoundsException exception) {
            return ui.showError(exception);
        }
    }
}
