package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markDone(taskNum);
        storage.save(tasks.getTasks());
    }
}
