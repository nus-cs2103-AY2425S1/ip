package genji.command;

import genji.GenjiException;
import genji.task.TaskList;
import genji.task.Task;
import genji.Ui;
import genji.Storage;
public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        try {
            Task temp = list.get(index);
            list.delete(index);
            ui.delete(temp, list);
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
