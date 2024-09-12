package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an abstract user command into the chatbot
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface, and storage.
     * Subclasses will implement the specific command logic.
     *
     * @param taskList The task list on which the command is executed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save tasks.
     * @throws IOException If there is an error during rewriting the local data.
     */
    public abstract String execute(TaskList taskList, UI ui, Storage storage) throws IOException;
}
