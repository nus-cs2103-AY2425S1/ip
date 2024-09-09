package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;

import java.io.IOException;

/**
 * Represents an abstract command in the chatbot.
 *
 * A command encapsulates an action that can be executed, which involves interacting with
 * a list of tasks, the user interface, and storage. Specific implementations of commands
 * will define how these interactions occur.
 *
 * @author Choi Yi Hao
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException;
    public abstract boolean isExit();
}
