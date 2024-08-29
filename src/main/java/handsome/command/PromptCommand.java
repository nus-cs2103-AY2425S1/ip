package handsome.command;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;

/**
 * The PromptCommand class handles the "prompt" command, which
 * displays a list of available commands to the user within the Handsome chatBot.
 */
public class PromptCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showPrompt();
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
