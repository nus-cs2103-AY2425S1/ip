package command;

import storage.StorageOperationException;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui) throws StorageOperationException, IndexOutOfBoundsException {
        list.deleteTask(index);
    }
}
