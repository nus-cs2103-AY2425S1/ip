package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.unmarkTask(index);
        ui.showMessage("jude.task.Task " + list.getTask(index) + " been unmarked.");
        storage.save(list);
    }

    @Override
    public String toString() {
        return "UnmarkCommand";
    }
}
