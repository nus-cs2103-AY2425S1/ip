package miku.command;

import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

import miku.task.Task;


/**
 * Adds a task to the task list
 */
public class AddCommand extends Command {
    Task task;

    /**
     * Initialise an add command.
     *
     * @param task the task to be added to the task list.
     */
    public AddCommand(Task task){
        this.task = task;
    }

    /**
     * Execute the command to add a task to a task list.
     *
     * @param taskList The task list that takes in the new task.
     * @param ui The UI to perform printing.
     * @param storage The storage function to store the data into a text file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.addItem(task);
        storage.saveFromTaskList(taskList);
        ui.printSectionBreak();
    }
}
