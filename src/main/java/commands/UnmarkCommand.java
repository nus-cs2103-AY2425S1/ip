package commands;

import exceptions.AtlasException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        if (index < 0 || index >= tasks.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = tasks.unmark(this.index);
        storage.save();
        ui.print(String.format("OK, I've marked this task as not done yet:\n \t%s", task));
    }
}
