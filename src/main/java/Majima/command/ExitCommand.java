package Majima.command;

import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.ui.Ui;

/**
 * When an ExitCommand is called upon, it exits the main loop of the chatbot.
 * This ends the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
