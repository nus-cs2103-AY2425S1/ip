package gavinchatbot.command;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;
import java.io.IOException;

/**
 * Represents a command that can be executed in the GavinChatBot application.
 */
public interface Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to be manipulated by this command.
     * @param ui The UI that will show the output to the user.
     * @param storage The storage where the task list is saved.
     * @throws GavinException If there is an error during the execution.
     * @throws IOException If there is an error saving the task list to storage.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException;

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return true if the application should exit after this command, false otherwise.
     */
    boolean isExit();
}
