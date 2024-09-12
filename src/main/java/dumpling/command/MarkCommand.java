package dumpling.command;

import dumpling.DumplingException;
import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * MarkCommand class for mark AND unmark commands, inherits Command
 */
public class MarkCommand extends Command {

    private CommandEnum commandEnum;
    private int itemIdx;

    /**
     * Command for marking and unmarking items.
     *
     * @param commandEnum Command enum to flag if this action is to unmark or mark a task
     * @param itemIdx 1-based index of which item to mark or unmark
     */
    public MarkCommand(CommandEnum commandEnum, int itemIdx) {
        this.commandEnum = commandEnum;
        this.itemIdx = itemIdx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        String message = "";
        try {
            if (commandEnum == CommandEnum.MARK) {
                message = taskList.mark(itemIdx);
            } else if (commandEnum == CommandEnum.UNMARK) {
                message = taskList.unmark(itemIdx);
            } else {
                // this function is only called if MARK or UNMARK is used as the command
            }
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DumplingException(
                    "You tried to mark / unmark at an index out of range! "
                    + String.format("There are only %d items.", taskList.getNumItems()));
        }
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
