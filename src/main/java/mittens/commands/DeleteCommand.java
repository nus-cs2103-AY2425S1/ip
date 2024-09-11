package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

public class DeleteCommand extends Command {
    protected int index;

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
