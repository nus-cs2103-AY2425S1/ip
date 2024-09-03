package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;

/**
 * Executes the command that displays the exit page.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
