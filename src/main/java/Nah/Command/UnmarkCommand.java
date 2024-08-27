package Nah.Command;

import Nah.Exceptions.NahException;
import Nah.Storage.Storage;
import Nah.TaskList.TaskList;
import Nah.UI.UI;

public class UnmarkCommand extends Command{
    private int idx;
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
        ui.show(tasks.unMark(this.idx));
        storage.rewrite(tasks.brief());
    }
}
