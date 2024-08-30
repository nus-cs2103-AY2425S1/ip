package kitty.command;

import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;
    private Storage storage;

    public DeleteCommand(Ui ui, TaskList taskList, int index, Storage storage) {
        super(ui, taskList);
        this.index = index;
        this.storage = storage;
    }

    @Override
    public void run() throws IndexOutOfBoundsException{
        try {
            String msg = tasks.deleteTask(index);
            storage.rewriteFile(tasks.getData());
            ui.showDeleteTaskMessage(msg);
        } catch (IOException e) {
            ui.showErrorMessage("File writing unsuccessful.\n"
                    + "File deletion is not updated to hard disk.");
        }
    }
}