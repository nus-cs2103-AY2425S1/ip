package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkTaskCommand extends Command {
    private int index;

    /**
     * Creates an UnmarkTaskCommand with the specified index of the task to be unmarked as not done.
     *
     * @param index the index of the task to be unmarked as not done in the task list
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark task command by marking the task at the specified index as not done,
     * saving the updated task list, and returning a message from the Ui.
     * If the index is invalid, it will return an error message.
     *
     * @param list the task list where the task will be unmarked as not done
     * @param ui the user interface to interact with the user
     * @param storage the storage to save the updated task list
     * @return a string representing the result of unmarking the task or an error message if the index is invalid
     * @throws IOException if an error occurs while saving the task list to storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.markNotDone(index);
        storage.saveFile(list);
        return ui.unmarkTask(list.getTask(index));
    }
}
