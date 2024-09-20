package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          Stack<Command> commandStack) throws IOException, HandsomeException {
        assert taskNum >= 0 && taskNum <= tasks.getTasks().size() : "Hey there, try a number within range?";
        String deletedTask = tasks.deleteCheck(this.taskNum);
        storage.save(tasks.getTasks());
        return deletedTask;
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws IOException, HandsomeException {
        assert tasks != null : "Can't delete non existent things haha...";
        String completed = tasks.addTaskAtIndex(taskNum);
        storage.save(tasks.getTasks());
        return completed;
    }
}
