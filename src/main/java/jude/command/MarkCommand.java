package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.markTask(index);
        ui.showMessage("jude.task.Task " + list.getTask(index) + " has been marked.");
        storage.save(list);
    }
}
