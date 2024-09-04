package dumpling.command;

import dumpling.task.TaskList;
import dumpling.Ui.Ui;
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

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        String message = taskList.delete(this.itemIdx);
        storage.save(taskList);
        return  message;
    }

    public boolean isExit() {
        return false;
    }
}
