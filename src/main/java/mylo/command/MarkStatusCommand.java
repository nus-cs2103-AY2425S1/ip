package mylo.command;


import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Ui;

public class MarkStatusCommand extends Command {
    private boolean isMarkCommand;
    private int index;

    public MarkStatusCommand(boolean isMarkCommand, int index) {
        this.isMarkCommand = isMarkCommand;
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui) throws StorageOperationException {
        String message = isMarkCommand ? list.markTaskAsDone(index) : list.markTaskAsUndone(index);
        ui.show(message);
    }
}
