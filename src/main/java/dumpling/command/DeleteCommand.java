package dumpling.command;

import dumpling.DumplingException;
import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * DeleteCommand class, inherits Command
 */
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

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DumplingException {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) throws DumplingException {
        try {
            String message = taskList.delete(this.itemIdx);
            storage.save(taskList);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DumplingException(
                    "    Grrr... You tried to delete at an index out of range! "
                            + String.format("There are only %d items.", taskList.getNumItems()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
