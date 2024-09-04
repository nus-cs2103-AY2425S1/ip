package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.TaskList;

/**
 * Represents a template for all commands to be executed.
 * It consists of methods for execution and exiting.
 *
 * @author Wai Hong
 */

public abstract class Command {

    /**
     * Executes the given command according to preset algorithm.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage where tasks are stored.
     * @throws Exception Exceptions that are thrown during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Provides boolean on whether the programmes should exit.
     *
     * @return A boolean value whether the programme should exit.
     */
    public abstract boolean isExit();
}
