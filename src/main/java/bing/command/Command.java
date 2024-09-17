package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

/**
 * Represents command that can be executed in the task management system.
 */
public interface Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for tasks
     */
    void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return true if the application should exit, false otherwise
     */
    boolean isExit();
}
