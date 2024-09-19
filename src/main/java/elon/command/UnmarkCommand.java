package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.io.IOException;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.markNotDone(index);
        storage.saveFile(list);
        return ui.unmarkTask(list.getTask(index));
    }
}
