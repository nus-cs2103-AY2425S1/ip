package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for marking a task as done in the task list.
 */
public class MarkCommand extends Command {
    
    protected int index;

    /**
     * Creates a new MarkCommand object with the specified index of the task to mark as done.
     * 
     * @param index The index of the task to mark as done (1-indexed)
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTaskAsDone(this.index - 1);

            storage.save(tasks);

            ui.showRegularMessage("Meow, I scratched the check box for you:", task.toString());
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
