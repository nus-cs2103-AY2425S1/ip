package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            ui.showTaskMarked(task);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new MajimaException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
