package Nah.Command;

import Nah.Exceptions.NahException;
import Nah.Storage.Storage;
import Nah.TaskList.TaskList;
import Nah.UI.UI;

public class MarkCommand extends Command{
    private int idx;
    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
        ui.show(tasks.mark(this.idx));
        storage.rewrite(tasks.brief());
    }
}
