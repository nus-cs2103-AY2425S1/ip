package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that indicates an invalid command input.
 */
public class InvalidCommand implements Command {

    /**
     * Executes the command by displaying an error message for invalid commands.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save or load tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.showError("Invalid command!");
    }

    /**
     * Indicates that this command does not cause the program to terminate.
     *
     * @return false, as this command does not result in program termination.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
