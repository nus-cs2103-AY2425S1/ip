package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.Stack;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Stack<Command> commandRecord) {
        return "Hey it has been a nice conversation, good day ahead!";
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "Guess who's back? It's me handsome hahahaha.";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
