package command;

import task.TaskList;
import exception.ScheduloException;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        tasks.unmark(index - 1);
        storage.save(tasks);
    }
}
