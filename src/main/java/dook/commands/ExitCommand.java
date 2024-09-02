package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that the user uses to exit the chatbot.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        storage.write(tasks);
        ui.exit();
        super.isExit = true;
    }
}
