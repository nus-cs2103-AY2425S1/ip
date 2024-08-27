package gray.command;

import gray.GrayException;
import gray.Ui;
import gray.TaskList;

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
    public boolean isExit() { return false; }
}
