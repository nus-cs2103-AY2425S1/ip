package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;


/**
 * Represents a command to undo the previous command.
 */
public class CommandUndo extends Command {

    public CommandUndo(String argument) {
        isUndoable = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isUndo = true;
    }
}
