package action;

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
        return "Goodbye! Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
