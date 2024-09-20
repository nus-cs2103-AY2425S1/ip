package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    
    protected int index;

    /**
     * Creates a new DeleteCommand object with the specified index of the task to delete.
     * 
     * @param index The index of the task to delete (1-indexed)
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.deleteTask(this.index - 1);

            storage.save(tasks);

            ui.showRegularMessage("Meow, I deleted the task \"%s\" for you :3"
                    .formatted(deletedTask.getDescription()));
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
