package miku.command;

import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

/**
 * Unmarks a specified task.
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * Initialise a command to unmark the i th task.
     *
     * @param index the index of the task to be unmarked.
     */
    public UnmarkCommand(int index){
        this.index = index;
    }

    /**
     * Executes the command to mark a task in a task list.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui The UI to perform printing.
     * @param storage The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unmark(index);
        ui.printSectionBreak();
        storage.saveFromTaskList(taskList);
    }
}
