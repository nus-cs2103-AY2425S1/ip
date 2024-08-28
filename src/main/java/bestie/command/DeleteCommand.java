package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class DeleteCommand extends Command {
    // delete tasks from list
    private int index; // item to be deleted
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (this.index >= 0 && this.index < tasks.size()) {
            tasks.deleteTask(this.index);
            ui.showTaskDeleted(tasks.size());
        } else {
            ui.showIndexOutOfBoundsMessage(this.index);
        }
    }
}
