package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.Task;
import noosy.task.TaskList;

public class MarkCommand extends Command {

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        try {
            Task completed = tasks.get(this.index);
            completed.markDone();
            ui.showTaskDone(completed);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NoosyException("This task number is unavailable at the moment. \n " +
                    "Please try again later.");
        }
    }
}