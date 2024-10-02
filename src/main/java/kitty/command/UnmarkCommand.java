package kitty.command;

import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;
import kitty.tasks.Task;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int index;
    private final Storage storage;
    public UnmarkCommand(Ui ui, TaskList tasks, int index, Storage storage) {
        super(ui, tasks);
        this.index = index;
        this.storage = storage;
    }

    @Override
    public String run() throws IndexOutOfBoundsException {
        Task tmp = tasks.markUndone(index);
        try {
            storage.rewriteFile(tasks.getData());
            return ui.showUndoneMessage(tmp);
        } catch (IOException e) {
            return ui.showErrorMessage("File writing unsuccessful.\n"
                    + "This task is not updated as Done on hard disk.");
        }
    }
}
