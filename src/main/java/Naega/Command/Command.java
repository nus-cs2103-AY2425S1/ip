package Naega.Command;

import Naega.NaegaException;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage components.
     *
     * @param tasks   the task list to be manipulated by the command
     * @param ui      the UI component to display information to the user
     * @param storage the storage component to save changes
     * @return
     * @throws NaegaException if an error occurs during command execution
     * @throws IOException    if an I/O error occurs
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NaegaException, IOException;

    /**
     * Checks if the command signifies an exit action.
     *
     * @return true if the command is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}