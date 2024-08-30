package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String delString = tasks.deleteTask(index);
        ui.delete(delString);
    }

}
