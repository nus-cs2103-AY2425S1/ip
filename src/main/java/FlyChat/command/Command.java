package flychat.command;

import java.util.InputMismatchException;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @return The response to the user.
     * @throws InputMismatchException If the input is invalid.
     */
    public abstract String execute(TaskList taskList, Ui ui, Parser parser, String inputString) 
            throws InputMismatchException;
}
