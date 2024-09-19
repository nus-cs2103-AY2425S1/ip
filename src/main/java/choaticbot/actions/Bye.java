package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.tasks.TaskList;
import choaticbot.ui.MainWindow;
import choaticbot.ui.Ui;

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
    public Bye(TaskList taskList) {
        super(taskList);
    }

    /**
     * Executes the {@code Bye} action. This action is intended to terminate the session,
     * and thus, it returns a farewell message without performing any other operation.
     *
     * @return an {@link ActionResult} containing the goodbye message
     * @throws ChoaticBotException if an error occurs during execution
     */
    @Override
    public ActionResult execute() throws ChoaticBotException {
        MainWindow.handleExit();
        return new ActionResult(Ui.getByeMsg());
    }

    /**
     * Indicates that this action signifies the end of the program.
     *
     * @return {@code true}, indicating that this action terminates the session
     */
    @Override
    public boolean isEnd() {
        return true;
    }
}
