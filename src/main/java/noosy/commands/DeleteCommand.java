package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.Task;
import noosy.task.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        try {
            Task toDelete = tasks.get(this.index);
            tasks.remove(toDelete);
            ui.showDeleted(toDelete);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NoosyException("This task number is unavailable at the moment. \n " +
                    "Please try again later.");
        }
    }
}
