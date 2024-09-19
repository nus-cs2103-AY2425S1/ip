package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.io.IOException;

public class MarkTaskCommand extends Command {
    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.markDone(index);
        storage.saveFile(list);
        return ui.markTask(list.getTask(index));
    }
}
