package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        String undone = tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
        return undone;
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String undo = tasks.markDone(taskNum);
        storage.save(tasks.getTasks());
        return undo;
    }
}
