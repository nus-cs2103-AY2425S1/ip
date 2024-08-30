package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;

public class UnmarkCommand extends Command {
    private int pos;

    public UnmarkCommand(int pos) {
        this.pos = pos;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (pos > 0 && pos <= tasks.getLength()) {
//                        tasks.get(pos - 1).unmarkDone();
            tasks.unmarkTask(pos - 1);
            ui.talk("No prob, have marked as undone: " + tasks.getTaskString(pos - 1));
        } else {
            ui.showError("please choose a number between 1 and " + tasks.getLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
