package Darkpool.Command;

import Darkpool.util.Storage;
import Darkpool.util.TaskList;
import Darkpool.util.Ui;

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
