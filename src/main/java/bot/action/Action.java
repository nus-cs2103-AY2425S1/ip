package bot.action;

import bot.exceptions.BotException;
import bot.tasks.TaskList;

/**
 * Abstract class representing a generic executable action.
 *
 * @author mongj
 */
public abstract class Action {
    /**
     * A boolean indicating if the action is a terminal action.
     * The program will exit if action is terminal.
     * Defaults to false.
     */
    public boolean isTerminal = false;

    /**
     * A boolean indicating if the action can be undone.
     * Defaults to true.
     */
    public boolean canUndo = true;

    /**
     * Executes the action
     *
     * @param taskList to execute the action on.
     * @return A message representing the result of the execution.
     * @throws BotException if the action could not be executed.
     */
    public abstract String execute(TaskList taskList) throws BotException;
}