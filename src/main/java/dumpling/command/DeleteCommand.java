package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui;
import dumpling.Storage;

public class DeleteCommand extends Command {

    private int itemIdx;

    /**
     * Command for deleting items from the TaskList
     *
     * @param itemIdx target 1-indexed item to be deleted
     */
    public DeleteCommand(int itemIdx) {
        this.itemIdx = itemIdx;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.delete(this.itemIdx);
        ui.echo(message);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
