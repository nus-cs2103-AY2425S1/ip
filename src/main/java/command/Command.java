package command;

import exceptions.DelphiException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Abstract class representing a command.
 * command are used to perform various actions within the application.
 *
 * @author jordanchan
 */
public abstract class Command {

    private String input;
    private boolean isExit;

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
    public abstract String execute(TaskList t, Storage s, Ui ui, Parser p) throws DelphiException;

    public String getInput() {
        return this.input;
    }
}

