package ai.command;

import ai.exception.AiException;
import ai.TaskList;
import ai.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws AiException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
