package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;


/**
 * The command class represents the abstract template for all commands to follow.
 */
public abstract class Command {
    /**
     * Executes the respective command associated with the class.
     * Displays the task message on console through the Ui given.
     * Saves any changes made to the data.txt file through the Storage given.
     *
     * Throws ChatBotException with the respective error message when the command
     * is unable to be executed successfully.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @throws ChatBotException if command is unable to execute successfully
     */
    public abstract void execute(TaskList tasks,
                                 Ui ui, Storage storage) throws ChatBotException;

    /**
     * Returns the status on whether the program should terminate.
     *
     * @return a boolean representing whether the program should terminate
     */
    public abstract boolean isExit();
}
