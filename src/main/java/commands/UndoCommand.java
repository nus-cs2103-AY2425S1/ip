package commands;

import exceptions.HandsomeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Stack;

public class UndoCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          Stack<Command> commandRecords) throws HandsomeException, IOException {
        if (!commandRecords.isEmpty()) {
            Command lastCommand = commandRecords.pop();
            lastCommand.undo(tasks, ui, storage);
            return "Well, I have undone your previous action, make up your mind ok?";
        } else {
            return "Unfortunately, I cannot undo what is non-existent.";
        }
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        return "Undo operation cannot be undone.";
    }
}
