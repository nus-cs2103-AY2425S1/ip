package commands;

import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.util.Stack;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecords) {
        return tasks.printList();
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "I am unfortunately, unable to undo such a command.";
    }
}
