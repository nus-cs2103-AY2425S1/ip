package kitty.command;

import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;
import kitty.tasks.Task;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int index;
    private final Storage storage;
    public MarkCommand(Ui ui, TaskList tasks, int index, Storage storage) {
        super(ui, tasks);
        this.index = index;
        this.storage = storage;
    }

    @Override
    public String run() throws IndexOutOfBoundsException {
        Task tmp = tasks.markDone(index);
        try {
            storage.rewriteFile(tasks.getData());
            return ui.showDoneMessage(tmp);
        } catch (IOException e) {
            return ui.showErrorMessage("File writing unsuccessful.\n"
                    + "This task is not updated as Done on hard disk.");
        }
    }
}
