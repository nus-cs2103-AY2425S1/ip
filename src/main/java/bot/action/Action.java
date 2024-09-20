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
    private boolean isTerminal = false;

    /**
     * A boolean indicating if the action can be undone.
     * Defaults to true.
     */
    private boolean canUndo = true;

    /**
     * Gets the terminal status of the action.
     *
     * @return a boolean indicating if the action is terminal.
     */
    public boolean isTerminal() {
        return this.isTerminal;
    }

    /**
     * Sets the terminal status of the action.
     *
     * @param isTerminal indicating if the action is terminal.
     */
    public void setIsTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    /**
     * Gets the canUndo status of the action.
     *
     * @return canUndo indicating if the action can be undone.
     */
    public boolean canUndo() {
        return this.canUndo;
    }

    /**
     * Sets the canUndo status of the action.
     *
     * @param canUndo indicating if the action can be undone.
     */
    public void setCanUndo(boolean canUndo) {
        this.canUndo = canUndo;
    }

    /**
     * Executes the action.
     *
     * @param taskList to execute the action on.
     * @return A message representing the result of the execution.
     * @throws BotException if the action could not be executed.
     */
    public abstract String execute(TaskList taskList) throws BotException;
}
