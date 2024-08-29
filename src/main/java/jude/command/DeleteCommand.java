package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.deleteTask(index);
        ui.showMessage("Task has been deleted.");
        storage.save(list);
    }

    @Override
    public String toString() {
        return "DeleteCommand";
    }
}
