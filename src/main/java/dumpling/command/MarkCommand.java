package dumpling.command;

import dumpling.DumplingException;
import dumpling.task.TaskList;
import dumpling.Ui;
import dumpling.Storage;

public class MarkCommand extends Command {

    private CommandEnum commandEnum;
    private int itemIdx;

    public MarkCommand(CommandEnum commandEnum, int itemIdx) {
        this.commandEnum = commandEnum;
        this.itemIdx = itemIdx;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "";
        try {
            if (commandEnum == CommandEnum.MARK) {
                message = tasks.mark(itemIdx);
            } else if (commandEnum == CommandEnum.UNMARK) {
                message = tasks.unmark(itemIdx);
            }
            ui.echo(message);
        } catch (IndexOutOfBoundsException e) {
            throw new DumplingException(
                    "You tried to mark / unmark at an index out of range! " +
                    String.format("There are only %d items.", tasks.getNumItems()));
        }
    }

    public boolean isExit() {
        return false;
    }
}
