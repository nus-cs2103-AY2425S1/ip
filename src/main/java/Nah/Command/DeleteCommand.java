package Nah.Command;

import Nah.Exceptions.NahException;
import Nah.Storage.Storage;
import Nah.TaskList.TaskList;
import Nah.UI.UI;

public class DeleteCommand extends Command{
    private int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
        ui.show(tasks.delete(this.idx));
        storage.rewrite(tasks.brief());
    }
}
