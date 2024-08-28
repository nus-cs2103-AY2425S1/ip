package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class ExitCommand extends Command {

    /**
     * Saves the tasks in the user's task list into the bestie.txt file.
     * Prints exit goodbye message.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // only save the tasks that are still in the list when exiting
        storage.saveTasksToFile(tasks);
        ui.byeBye();
    }
}
