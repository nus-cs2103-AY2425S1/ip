package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
    }
}
