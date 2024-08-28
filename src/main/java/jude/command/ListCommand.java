package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        ui.showMessage(list.toUiFormat());
    }

    @Override
    public String toString() {
        return "ListCommend";
    }
}
