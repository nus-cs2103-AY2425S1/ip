package action;

import task.TaskList;

/**
 * An action to be executed by BotManager.
 *
 * @author dwsc37
 */
public abstract class Action {
    public abstract String execute(TaskList taskList);
}
