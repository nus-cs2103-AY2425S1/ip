package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;
import bestie.task.Task;

/**
 * Creates a command to add a new task to the list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates a new instance of the command to add a new task to the list of tasks.
     *
     * @param task New task to be added to the list of tasks.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the user's existing list of tasks.
     * Prints message on the console to show that task has been successfully added.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showTaskAdded(task, tasks.size());
    }

}
