package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Ui;
import sage.Storage;

public class MarkCommand extends Command {

    private final String indexString;
    private final boolean isDone;

    public MarkCommand(String indexString, boolean isDone) {
        this.indexString = indexString;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        int index;
        try {
            index = Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SageException("Invalid mark/unmark command. Index must be a number.");
        }

        if (index < 0 || index >= tasks.getSize()) {
            throw new SageException("Invalid task number.");
        }

        tasks.markTask(index, isDone);
    }
}
