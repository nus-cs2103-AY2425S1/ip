package fishman.command;

import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * The Command interface represents an executable command in the task operations.
 * It defines the methods that all command classes must implement, specifying how
 * they should be executed or whether the program should exit.
 */
public interface Command {
    /**
     * Executes the command, performing actions on the tasklist and user interface.
     *
     * @param tasks The tasks to be added.
     * @param ui The Ui object used when displaying the message.
     */
    void execute(TaskList tasks, Ui ui);

    /**
     * Indicator for whether the program should exit.
     *
     * @return true if the program should exit after executing
     * this command, false otherwise
     */
    default boolean isExit() {
        return false;
    }
}

