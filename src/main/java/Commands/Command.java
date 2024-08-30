package Commands;

import Exceptions.DelphiException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

/**
 * Abstract class representing a command.
 * Commands are used to perform various actions within the application.
 *
 * @author jordanchan
 */
public abstract class Command {
    private boolean isExit;
    public String input;

    /**
     * Constructs a Command with the given input.
     *
     * @param s The input string associated with the command.
     */
    public Command(String s) {
        input = s;
    }

    /**
     * Executes the command with the specified task list, storage, and user interface.
     *
     * @param t  The task list to operate on.
     * @param s  The storage to use for saving/loading data.
     * @param ui The user interface to interact with the user.
     * @throws DelphiException If an error occurs during execution.
     */
    public abstract void execute(TaskList t, Storage s, UI ui) throws DelphiException;

    /**
     * Determines if the command should exit the application.
     *
     * @return True if the command indicates that the application should exit; otherwise, false.
     */
    public boolean exitBot() {
        return false;
    }
}

