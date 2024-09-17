package main.command;

import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that terminates the chatbot.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        toggleIsExit();
        ui.showBye();
    }
}
