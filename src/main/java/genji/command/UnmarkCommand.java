package genji.command;

import genji.GenjiException;
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
        try {
            list.unmark(index);
            ui.unmark(list.get(index));
            s.saveList(list);
        } catch (IndexOutOfBoundsException i) {
            ui.showError(new GenjiException(
                    "Please input a integer smaller than the number of tasks").getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
