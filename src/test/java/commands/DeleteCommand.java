package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException {
        tasks.deleteCheck(this.taskNum);
        storage.save(tasks.getTasks());
    }
}
