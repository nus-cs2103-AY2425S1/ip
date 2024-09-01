package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;
public class UnmarkCommand extends Command{
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.unmark(index);
        ui.unmark(list.get(index));
        s.saveList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
