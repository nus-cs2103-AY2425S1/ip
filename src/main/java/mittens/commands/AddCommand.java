package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

public class AddCommand extends Command {
    protected Task toAdd;

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
