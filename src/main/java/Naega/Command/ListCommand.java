package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list using the UI.
     *
     * @param tasks the task list to display
     * @param ui the UI component to display the tasks
     * @param storage the storage component (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        ui.showLine();
    }
}