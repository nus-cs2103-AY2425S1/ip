package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;

/**
 * Executes the default prompt when user enters an unknown command.
 */
public class DefaultCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) throws AiException {
        return "Sorry, I don't quite understand what you mean..."
                + "wanna try typing smth else??\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
