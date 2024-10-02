package kitty.command;

import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;
import kitty.tasks.Task;

import java.io.IOException;

public class TagCommand extends Command {
    private final int index;
    private final String tag;
    private final Storage storage;

    public TagCommand(Ui ui, TaskList tasks, int index, String tag, Storage storage) {
        super(ui, tasks);
        this.index = index;
        this.tag = tag;
        this.storage = storage;
    }

    @Override
    public String run() throws IndexOutOfBoundsException {
        Task tmp = tasks.editTag(index, tag);
        try {
            storage.rewriteFile(tasks.getData());
            return ui.showTagTaskMessage(tmp.toString());
        } catch (IOException e) {
            return ui.showErrorMessage("File writing unsuccessful.\n"
                    + "This task is not updated as Done on hard disk.");
        }
    }
}
