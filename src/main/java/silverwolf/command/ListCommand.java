package silverwolf.command;

import silverwolf.storage.Storage;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;

/**
 * The ListCommand class represents a command to display all tasks in the task list.
 * It extends the abstract Command class and implements the logic to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks currently in the task list.
     * It retrieves the tasks from the TaskList object and displays them to the user
     * in a formatted manner.
     *
     * @param tasks The TaskList object representing the list of tasks to be displayed.
     * @param ui The Ui object used for interacting with the user.
     * @param storage The Storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine(); // Display a line to separate sections in the UI.
        System.out.println("Here are the tasks in your list:");
        // Iterate through the tasks and display each one with its corresponding index.
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "" + tasks.getTasks().get(i));
        }
        ui.showLine();
        ui.newline();
    }
}
