package mittens;

import mittens.ui.Ui;

public class UnmarkCommand extends Command {
    protected int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTaskAsNotDone(this.index);

            storage.save(tasks);

            ui.showRegularMessage("Meow, I scratched the check box for you:", task.toString());
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
