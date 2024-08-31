package miku.command;


import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

/**
 * Marks a specified task as completed
 */
public class MarkCommand extends Command {
    int index;

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
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.mark(index);
        storage.saveFromTaskList(taskList);
        ui.printSectionBreak();
    }

}
