package elon.command;

import java.io.IOException;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Creates a DeleteTaskCommand with the specified index of the task to be deleted.
     *
     * @param index the index of the task to be deleted from the task list
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command by removing the task at the specified index from the task list,
     * saving the updated list, and returning a message from the Ui. If the index is invalid,
     * it will return an error message.
     *
     * @param list the task list from which the task will be deleted
     * @param ui the user interface to interact with the user
     * @param storage the storage to save the updated task list
     * @return a string representing the result of deleting the task or an error message if the index is invalid
     * @throws IOException if an error occurs while saving the task list to storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        Task removedTask = list.removeTask(index);
        storage.saveFile(list);
        return ui.deleteTask(removedTask, list);
    }
}
