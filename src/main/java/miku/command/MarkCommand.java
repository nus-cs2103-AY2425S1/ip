package miku.command;

import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.Response;



/**
 * Marks a specified task as completed
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Initialises a mark command.
     *
     * @param index the index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the command to add a task to a task list
     *
     * @param taskList The task list that takes in the new task
     * @param ui       The UI to perform printing
     * @param storage  The storage function to store the data into a text file
     */
    @Override
    public void execute(TaskList taskList, Response ui, Storage storage) {
        taskList.mark(index);
        storage.saveFromTaskList(taskList);
        ui.setResponse(taskList.get(index).doneStringValue());
    }

}
