package action;

import enums.StatusMessage;
import exception.BotException;
import task.TaskList;

/**
 * Action to exit BotManager.
 *
 * @author dwsc37
 */
public class ByeAction extends Action {
    @Override
    public String execute(TaskList taskList) throws BotException {
        return StatusMessage.GOODBYE.getMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
