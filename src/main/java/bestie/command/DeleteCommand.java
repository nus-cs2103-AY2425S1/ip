package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates a command to remove a specified task from the list.
 */
public class DeleteCommand extends Command {
    private int index; // index of task to be deleted

    /**
     * Creates a new instance of the command to delete a task from the list of tasks.
     *
     * @param index New task to be added to the list of tasks.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Deletes the task from the user's existing list of tasks.
     * Prints message on the console to show that task has been successfully deleted, and number of remaining tasks.
     * Displays an error message if user has entered an invalid index.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     * @return String displaying message that task has been deleted, or index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean withinBounds = this.index >= 0 && this.index < tasks.size();
        if (withinBounds) {
            tasks.deleteTask(this.index);
            return ui.showTaskDeleted(tasks.size());
        } else {
            return ui.showIndexOutOfBoundsMessage(this.index, tasks);
        }
    }
}
