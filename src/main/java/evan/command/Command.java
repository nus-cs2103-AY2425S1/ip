package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

/**
 * Represents a Command that the chatbot can execute.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList TaskList that the chatbot currently stores.
     * @param ui       UI object that handles the user interactions.
     * @param storage  Storage object that saves the chatbot's data to the storage .txt file.
     * @throws NoSuchTaskException If the command attempts to perform an operation on a Task that doesn't exist.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException;

    /**
     * Returns true if the chatbot should exit the session after execution of the command, and false if the chatbot
     * should continue to prompt the user for input.
     */
    public abstract boolean isExit();
}


