package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

/**
 * Represents a command to terminate the program.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command, which prints the exit message and terminates the program.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save or load tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Print the exit message directly
        ui.showByeMessage();
    }

    /**
     * Indicates that this command causes the program to terminate.
     *
     * @return true, as this command results in program termination.
     */
    @Override
    public boolean isExit() {
        return true; // Indicates that the program should terminate
    }
}
