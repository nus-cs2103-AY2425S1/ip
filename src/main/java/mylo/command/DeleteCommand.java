package mylo.command;

import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Ui;

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
