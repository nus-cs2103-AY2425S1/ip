package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidIndexException;
import moimoi.task.Task;

public class UnmarkCommand extends Command {

    String indexString;

    public UnmarkCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            task.unmark();
            ui.showCompletionMessage("Oof, it's OK! Let's get it done soon ;)\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
