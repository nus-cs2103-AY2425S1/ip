package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Task;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtlasException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = tasks.delete(this.index);
        storage.save();
        String message = String.format("Noted. I've removed this task:\n\t%s\n Now you have %s tasks in the list.",
                task, tasks.size());
        ui.print(message);
    }
}
