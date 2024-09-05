package Gary.command;

import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;

/**
 * Represents an abstract command in the Gary chatbot.
 * All specific commands should extend this class and implement the necessary methods.
 */
public abstract class Command {

    /**
     * Executes the command. Each specific command will provide its own implementation of this method.
     *
     * @param taskLists The task list to be manipulated by the command.
     * @param ui The UI object for user interaction.
     * @param storage The storage object for saving or loading tasks.
     */
    public abstract void execute(TaskList taskLists, Ui ui, Storage storage);

    /**
     * Indicates whether this command causes the application to terminate.
     *
     * @return true if the command terminates the application, false otherwise.
     */
    public abstract boolean isBye();
}
