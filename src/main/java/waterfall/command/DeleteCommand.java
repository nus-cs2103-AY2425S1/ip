package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(index);
        tasks.delete(index);
        storage.updateTask(tasks.getTasks());
        ui.showDeleteMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
