package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates a command that unmarks a task in the list.
 */
public class UnmarkCommand extends Command {

    private int index; // index of task that has been marked

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as undone when user specifies the task that is incomplete.
     * Displays message indicating the task has been successfully unmarked, or an error message
     * if index is out of bounds.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     * @return String displaying that task has been unmarked, or index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        boolean withinBounds = this.index >= 0 && this.index < tasks.size();
        if (withinBounds) {
            tasks.getTask(this.index).markUndone();
            return ui.showTaskUnmarked(tasks.getTask(this.index));
        } else {
            return ui.showIndexOutOfBoundsMessage(this.index, tasks);
        }
    }
}
