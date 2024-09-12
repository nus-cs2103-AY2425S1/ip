package choaticbot.actions;

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
     * Executes the specific action. Each subclass must provide an
     * implementation of this method.
     */
    public abstract void execute();

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
