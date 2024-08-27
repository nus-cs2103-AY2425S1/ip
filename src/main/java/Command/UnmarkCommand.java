package Command;

import Exceptions.NahException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Exceptions.InvalidTaskNumberException;

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
