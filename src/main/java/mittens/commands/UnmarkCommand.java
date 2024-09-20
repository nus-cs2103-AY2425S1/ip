package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for marking a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    
    protected int index;

    /**
     * Creates a new UnmarkCommand object with the specified index of the task to mark as not done.
     * 
     * @param index The index of the task to mark as not done (1-indexed)
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTaskAsNotDone(this.index - 1);

            storage.save(tasks);

            ui.showRegularMessage("Meow, I scratched the check box for you:", task.toString());
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
