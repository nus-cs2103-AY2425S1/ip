package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) throws IOException {
        String doneTask = tasks.markDone(taskNum);
        storage.save(tasks.getTasks());
        return doneTask;
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String undo = tasks.markUndone(taskNum);
        storage.save(tasks.getTasks());
        return undo;
    }
}
