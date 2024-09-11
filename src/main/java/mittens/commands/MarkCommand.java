package mittens.commands;

import mittens.MittensException;
import mittens.storage.Storage;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.ui.Ui;

public class MarkCommand extends Command {
    protected int index;

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
