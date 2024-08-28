package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class UnmarkCommand extends Command {

    private int index; // index of task that has been marked

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index >= 0 && this.index < tasks.size()) {
            tasks.getTask(this.index).markUndone();
            ui.showTaskUnmarked(tasks.getTask(this.index));
        } else {
            ui.showIndexOutOfBoundsMessage(this.index);
        }
    }
}
