package elon.command;

import java.io.IOException;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;



/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkTaskCommand extends Command {
    private int index;

    /**
     * Creates a MarkTaskCommand with the specified index of the task to be marked as done.
     *
     * @param index the index of the task to mark as done in the task list
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark task command by marking the task at the specified index as done,
     * saving the updated task list, and returning a message from the Ui.
     * If the index is invalid (out of bounds), it will return an error message.
     *
     * @param list the task list where the task will be marked as done
     * @param ui the user interface to interact with the user
     * @param storage the storage to save the updated task list
     * @return a string representing the result of marking the task or an error message if the index is invalid
     * @throws IOException if an error occurs while saving the task list to storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.markDone(index);
        storage.saveFile(list);
        return ui.markTask(list.getTask(index));
    }
}
