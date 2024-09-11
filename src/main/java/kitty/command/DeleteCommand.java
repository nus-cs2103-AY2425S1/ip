package kitty.command;

import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;
    private final Storage storage;

    public DeleteCommand(Ui ui, TaskList taskList, int index, Storage storage) {
        super(ui, taskList);
        this.index = index;
        this.storage = storage;
    }

    @Override
    public String run() throws IndexOutOfBoundsException {
        try {
            String msg = tasks.deleteTask(index);
            storage.rewriteFile(tasks.getData());
            return ui.showDeleteTaskMessage(msg);
        } catch (IOException e) {
            return ui.showErrorMessage("File writing unsuccessful.\n"
                    + "File deletion is not updated to hard disk.");
        }
    }
}
