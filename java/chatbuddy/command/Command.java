package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents an executable command in the ChatBuddy program.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, UI, and storage.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The UI to interact with the user.
     * @param storage The storage to load/save tasks.
     * @throws ChatBuddyException If there is an error while executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException;

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return True if the command causes the application to exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
