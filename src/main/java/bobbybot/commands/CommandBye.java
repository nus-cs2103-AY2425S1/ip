package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class CommandBye extends Command {
    public CommandBye(String argument) {
        isUndoable = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Bye. Hope to see you again soon!");
        ui.stop();
    }
}
