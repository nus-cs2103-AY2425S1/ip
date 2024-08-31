package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates a command that displays all tasks in the list to the user.
 */
public class ListCommand extends Command {
    /**
     * Displays all the tasks in the user's task list.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks.getTasks());
    }

}
