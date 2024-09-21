package flychat.command;

import java.util.InputMismatchException;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The task list to be modified.
     * @param ui The user interface to interact with the user.
     * @param parser The parser to parse user input.
     * @param inputString The user input.
     * @return The response to the user.
     * @throws InputMismatchException If the input is invalid.
     */
    public abstract String execute(TaskList taskList, Ui ui, Parser parser, String inputString) 
            throws InputMismatchException;
}
