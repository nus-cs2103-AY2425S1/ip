package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

/**
 * Creates a command that marks a task in the list.
 */
public class MarkCommand extends Command {

    private int index; // index of task that has been marked

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done when user specifies the task that is completed.
     * Displays message indicating the task has been successfully completed, or an error message
     * if index is out of bounds.
     *
     * @param tasks User's list of tasks.
     * @param ui User Interface object that executes printing of message to console.
     * @param storage Loads list of tasks from file and writes tasks to the bestie.txt file.
     * @return String displaying that task has been marked, or index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index >= 0 && this.index < tasks.size()) {
            tasks.getTask(this.index).markTaskDone();
            return ui.showTaskMarked(tasks.getTask(this.index));
        } else {
            return ui.showIndexOutOfBoundsMessage(this.index, tasks);
        }

    }
}
