package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KukiShinobuException {
        throw new KukiShinobuException("Hmm... I don't quite understand what you mean!");
    }
}
