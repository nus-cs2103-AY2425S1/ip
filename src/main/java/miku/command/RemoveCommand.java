package miku.command;

import miku.exception.RemoveNullException;
import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.Response;


/**
 * Removes a task from the task list.
 */
public class RemoveCommand extends Command {
    private int index;

    /**
     * Initialises a remove command.
     *
     * @param index the index of the task to be removed.
     */
    public RemoveCommand(int index) {
        this.index = index;
    }


    /**
     * Execute the command to remove a task from a task list.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui       The UI to perform printing.
     * @param storage  The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, Response ui, Storage storage) {
        try {
            String deleteResponse = ui.deleteItemResponse(index, taskList);
            if (index < 1 || index > taskList.size()) {
                throw new RemoveNullException("In valid index");
            }
            taskList.deleteItem(index);
            storage.saveFromTaskList(taskList);
            ui.setResponse(deleteResponse);
        } catch (RemoveNullException e) {
            e.print();
        }

    }

}
