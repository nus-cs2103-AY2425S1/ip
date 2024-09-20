package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for adding a task into the task list.
 */
public class AddCommand extends Command {
    protected Task toAdd;

    /**
     * Creates a new AddCommand object with the specified task to add.
     * 
     * @param toAdd The task to add
     */
    public AddCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.toAdd);

            storage.save(tasks);

            ui.showRegularMessage("I've added \"%s\" to your list :3"
                    .formatted(this.toAdd.getDescription()));
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
