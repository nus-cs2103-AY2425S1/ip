package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.io.IOException;

public class UnmarkTaskCommand extends Command{
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
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
