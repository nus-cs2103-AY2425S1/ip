package choaticbot.actions;

import choaticbot.tasks.TaskList;

/**
 * The {@code Bye} class represents the action that signals the end of the bot's session.
 * It extends the {@link Action} class and provides an implementation that indicates
 * the program should terminate.
 */
public class Bye extends Action {

    /**
     * Constructor for the {@code Bye} action.
     *
     * @param taskList The task list associated with the action (not used in this case).
     */
    public Bye(TaskList tasklist) {
        super(tasklist);
    }

    /**
     * Executes the {@code Bye} action. Since this action is intended to terminate the session,
     * it performs no operation.
     */
    @Override
    //Do nothing
    public void execute() {
    }

    /**
     * Indicates that this action signifies the end of the program.
     *
     * @return {@code true}, indicating that this action terminates the session.
     */
    @Override
    public boolean isEnd() {
        return true;
    }
}
