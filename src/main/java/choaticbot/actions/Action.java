package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.tasks.TaskList;

/**
 * Abstract class representing an action that can be executed by the bot.
 * Each action operates on a {@link TaskList} and defines its own behavior
 * by implementing the {@link #execute()} method.
 */
public abstract class Action {

    /**
     * The task list associated with the action.
     */
    protected TaskList taskList;

    /**
     * Constructor for creating an Action with the given task list.
     *
     * @param taskList The task list on which this action will operate.
     */
    public Action(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes a specific action defined by the subclass. Each subclass must provide
     * its own implementation of this method.
     *
     * @return the result of the action's execution, represented by an {@link ActionResult}.
     *         The result may contain data or status information about the action.
     * @throws ChoaticBotException if an error occurs during the execution of the action.
     */
    public abstract ActionResult execute() throws ChoaticBotException;

    /**
     * Indicates whether this action signifies the end of the program. By
     * default, this returns {@code false}, but it can be overridden by subclasses
     * that represent terminating actions.
     *
     * @return {@code true} if the action signifies the end of the program, otherwise {@code false}.
     */
    public boolean isEnd() {
        return false;
    };
}
