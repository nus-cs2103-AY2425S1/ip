package gray.command;

import gray.GrayException;
import gray.TaskList;
import gray.Ui;

/**
 * A command abstract class to follow for the parser.
 */
public abstract class Command {

    /**
     * Executes the command on the ui and tasks.
     *
     * @param ui
     * @param tasks
     * @throws GrayException
     */
    public void execute(Ui ui, TaskList tasks) throws GrayException { }

    /**
     * Checks whether to stop the chat bot.
     *
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
