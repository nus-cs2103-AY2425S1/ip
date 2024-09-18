package action;

import exception.BotException;
import task.TaskList;

/**
 * Abstract class representing a generic action to be executed by BotManager.
 *
 * @author dwsc37
 */
public abstract class Action {
    /**
     * Executes the action, returning a message and possibly mutating the <code>taskList</code>.
     * May also throw a <code>BotException</code>, if the action could not be executed.
     *
     * @param taskList Task list to be mutated.
     * @return A message representing the result of the execution.
     * @throws BotException If the action could not be executed.
     */
    public abstract String execute(TaskList taskList) throws BotException;

    /**
     * Returns true if the action is an exit action.
     *
     * @return False by default, true if the action is an exit action.
     */
    public boolean isExit() {
        return false;
    }
}
