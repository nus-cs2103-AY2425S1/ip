package command;

import task.TaskList;
import exception.ScheduloException;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        tasks.mark(index - 1);
        storage.save(tasks);
    }
}
